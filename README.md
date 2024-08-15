# STREAM_ANALYSTIC_JAVA

The project also accounts for scenarios where the customer rejects the order. The events with their respective time intervals include:

- **CUSTOMER_ORDER_FOOD**: The customer places an order, with a 2-minute countdown for preparation.
- **DRIVER_ACCEPT**: After preparation, the driver has 1 minute to accept the delivery.
- **PICK_UP_FROM_RESTAURANT**: The driver picks up the order from the restaurant, with a 2-minute timer for this action.
- **DROP_OFF_TO_CUSTOMER**: The driver delivers the food to the customer, with a 5-minute timer for the drop-off.
- **CUSTOMER_REJECT**: Occasionally, the customer may reject the delivered food, which is logged as an event.



https://github.com/user-attachments/assets/e3fec346-fc9b-4cd9-af21-947b6a960523

