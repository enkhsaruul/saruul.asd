saruul.asd
==========

Applied following design patterns:

1.	Party pattern

In the Bank application, there are 2 types of Customers that have common attributes – Person and Company.
They Name and Email attribute. But they also have different attributes, birthday and number of employees. 
Having the same attributes on different objects is redundant. The Party pattern resolves this concern. 
With Party pattern, Person and Company objects will inherit from a common Customer object that contains the common attributes.
Also, they inherit from IPerson and ICompany interfaces which contains different attributes.

2.	Account pattern

Major use cases include being able to deposit and withdraw certain amounts from an account. 
The Account pattern designs the Account to be a container that keeps these transactions(entry), 
where we are able to keep historical values of balances. The Account is also a way to integrate the 
Customer object and the Transaction object since customers are allowed to have more than one account.

3.	Command pattern

We applied the Command pattern for deposit, withdraw and addInterest command. Our team designed the Command pattern and 
Account pattern together by one interface. It means our ICommand interface is also IEntry interface. Concrete classes of 
the 2 interfaces are called at the same time, if we design seperately. Concrete command and concrete entry classes contains 
same datas as well. That is why we merged them into only one interface. 

4.	Functor pattern

We used a Functor pattern for the sendEmail requirement. Whenever deposit and withdraw transactions are made,
the functors to check the sendEmail conditions.

5.	Factory Method Pattern

According to application requirements, we create Account and Customer. Both of them have a several types to create. For example, 
Account types are Bronze, Silver and Gold in the bank application. In the framework, Customer has Person and Company types.
We used Factory pattern for the creation of them. The advantage of using the pattern is that create an object without knowing 
the class exactly. It is encapsulating the class as well.

6.	Strategy pattern

The transactions deposit, withdraw, addInterest in Bank and Credit Card application are similar actions which acts with Account object.
The difference is just only in the algorithm. So, we decided to use Strategy pattern because it is easy to do changes and extend.

7.	Façade Pattern

With the subsystems (GUI, Business and Database), the team used Façade Pattern to make sure each of these subsystems interface each other using only a common method per subsystem. This pattern also indicates layering as entry point for every level in our system.

8.	Singleton Pattern

We applied the Singleton pattern for to send email. Because user email is unique. 




