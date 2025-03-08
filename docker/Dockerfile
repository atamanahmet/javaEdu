FROM python:3.8-slim
WORKDIR "/src/app/"
COPY . .
RUN pip install -r grade-submission/requirements.txt
EXPOSE 5000

# # CMD [ "python", "flask-demo/app.py" ]
# ENV FLASK_APP=flask-demo/app.py

CMD ["python", "grade-submission/app.py"]