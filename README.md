# cabas
 
### Prerequisites

_RabbitMQ_
```
https://www.rabbitmq.com/#getstarted
or
docker run -d --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management
```

---

### Alerter
Accept POST requests on http://localhost:8080/alert/initAreaAlert  
(You can use http://localhost:8080/swagger-ui.html to access swagger UI)  
For each relevant citizen it posts a message via RabbitMQ

Request example:
```
{
  "areas": [
    "Center",
    "Haifa",
    "Jerusalem",
    "North",
    "South",
    "Tel Aviv"
  ],
  "severity": "LOW"
}
```

### Notificator
Receive messages from RabbitMQ and send relevant notification to given citizen.