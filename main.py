import os
from fastapi import FastAPI, Depends, HTTPException
from pydantic import BaseModel
from typing import List
import uvicorn
from sqlalchemy import create_engine, Column, Integer, String
from sqlalchemy.ext.declarative import declarative_base
from sqlalchemy.orm import sessionmaker, Session

# Configuración de base de datos
DATABASE_URL = os.environ.get("DATABASE_URL", "sqlite:///./test.db")
engine = create_engine(DATABASE_URL)
SessionLocal = sessionmaker(autocommit=False, autoflush=False, bind=engine)
Base = declarative_base()

# Modelo de base de datos (SQLAlchemy)
class TaskDB(Base):
    __tablename__ = "tasks"
    id = Column(Integer, primary_key=True, index=True)
    title = Column(String)
    description = Column(String)
    priority = Column(String)

# Crear tablas
Base.metadata.create_all(bind=engine)

# Modelo Pydantic (para la API)
class Task(BaseModel):
    id: int
    title: str
    description: str
    priority: str

    class Config:
        from_attributes = True

app = FastAPI()

# Dependencia para obtener sesión
def get_db():
    db = SessionLocal()
    try:
        yield db
    finally:
        db.close()

@app.get("/tasks", response_model=List[Task])
async def get_tasks(db: Session = Depends(get_db)):
    return db.query(TaskDB).all()

@app.post("/tasks")
async def add_task(task: Task, db: Session = Depends(get_db)):
    db_task = TaskDB(title=task.title, description=task.description, priority=task.priority)
    db.add(db_task)
    db.commit()
    db.refresh(db_task)
    return db_task

@app.delete("/tasks/{task_id}")
async def delete_task(task_id: int, db: Session = Depends(get_db)):
    task = db.query(TaskDB).filter(TaskDB.id == task_id).first()
    if not task:
        raise HTTPException(status_code=404, detail="Task not found")
    db.delete(task)
    db.commit()
    return {"message": "Task deleted"}

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 8000))
    uvicorn.run("main:app", host="0.0.0.0", port=port)
