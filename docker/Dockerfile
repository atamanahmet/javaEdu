FROM python:3.8-slim
WORKDIR "/src/app/"
COPY . .
RUN pip install -r flask-mysql/requirements.txt
EXPOSE 3000

# # CMD [ "python", "flask-demo/app.py" ]
# ENV FLASK_APP=flask-demo/app.py

CMD ["python", "flask-mysql/app.py"]