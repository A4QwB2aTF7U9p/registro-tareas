FROM python:3.9

WORKDIR /app

COPY requirements.txt .
RUN pip install --no-cache-dir -r requirements.txt

COPY . .

# Usaremos la variable PORT proporcionada por Railway
CMD ["sh", "-c", "python main.py"]
