<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Checkout | FoodDeliveryApp</title>
    
    <!-- Link to the CSS file -->
    <link rel="stylesheet" href="checkout.css">
</head>
<body>

    <div class="container">
        <h2>Checkout</h2>
        
        <!-- Form mapping to your backend "checkout" servlet -->
        <form action="checkout" method="post">
            
            <!-- Delivery Address Field -->
            <label for="address">Delivery Address:</label>
            <textarea id="address" name="address" required placeholder="Enter your full delivery address here..."></textarea>
            
            <!-- Payment Method Field -->
            <label for="paymentMethod">Choose Payment Method:</label>
<select name="paymentMethod" id="paymentMethod" required>
    <!-- The 'value' must match the Database ENUM exactly -->
    <option value="CASH">Cash on Delivery (CASH)</option>
    <option value="CARD">Credit / Debit Card (CARD)</option>
    <option value="UPI">UPI Payment (UPI)</option>
</select>
            
            <!-- Submit Button -->
            <input type="submit" value="Place Order">
            
        </form>
    </div>

</body>
</html>