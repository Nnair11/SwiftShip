Login, Register and Interactive GUI with MySQL Database Connection

A simple application designed for efficient courier package management and tracking. It enables users to store package details, including sender and receiver information, and monitor delivery status in real time.
 
Table of Contents
Classes
AppLauncher
LoginFormGUI
RegisterFormGUI
Form
MyJDBC
CommonConstants
Classes


FEATURES:
	
User Management
Admin login and Authentication their Dashboard
Customer sign up and login
Delivery Staff Login

Package Management
Store package details (tracking number, sender, receiver, weight, delivery type)
Assign packages to delivery staff
Package pricing based on weight and distance


Report & Analytics
Track Package status (Pending, Picked Up, In Transit, Out for Delivery, Delivered)
Details of package (Customer Name, Address, Send to)
Customer Delivery History

Usage
To use the AppLauncher application:

Ensure you have a MySQL database set up with the required schema.
Update the database connection details in the CommonConstants class.
Compile and run the AppLauncher class.
The login form will appear, allowing users to log in with existing credentials or register for a new account.
