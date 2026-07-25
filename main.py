import os
from fastapi import FastAPI
from pydantic import BaseModel
from typing import List
import uvicorn

app = FastAPI()

# Modelo de datos simplificado
class Task(BaseModel):
    id: int
    title: str
    description: str
    priority: str

# Almacenamiento en memoria para el prototipo
tasks = []

@app.get("/tasks", response_model=List[Task])
async def get_tasks():
    return tasks

@app.post("/tasks")
async def add_task(task: Task):
    tasks.append(task)
    return task

@app.delete("/tasks/{task_id}")
async def delete_task(task_id: int):
    global tasks
    tasks = [t for t in tasks if t.id != task_id]
    return {"message": "Task deleted"}

if __name__ == "__main__":
    port = int(os.environ.get("PORT", 8000))
    uvicorn.run("main:app", host="0.0.0.0", port=port)
