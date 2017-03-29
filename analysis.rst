********
Analysis
********
It's possible to run analysis scripts on your computer, or inside Tago server. In the follow pages, you will be instructed on how to setup an analysis on your computer, use our services, and manage any data from Tago.

If you want to get instructions about how to upload your script or how to use third-party packages inside our server, take a look at `admin analysis documentation <http://docs.tago.io/en/latest/analysis.html>`_

Setting Up Analysis
*******************
Through analysis, it is possible to insert any calculation and manage your data from Tago in any way you want. We provide some services, such as SMS and email, but you are free to use any third party packages that you need.

To setup an analysis, you first need a analysis token. That can be retrieved from the `admin analysis section. <http://docs.tago.io/en/latest/analysis.html#setting-up-analysis>`_.

| **Syntax**
| *.listening(listener, "analysis token")*
|
| **Arguments**
| *listener a listener to be executed when the analysis runs.*
| *analysis_token(string) analysis token. Only needed if the script will run remotelly (Optional).*
|

.. code-block:: java

    Analysis myanalysis = new Analysis();
        
    Emitter.Listener listener = new Emitter.Listener() {
        @Override
        public void call(Object... context) {
            //Do anything you want here
        }
    };
    
    myanalysis.listening(listener, "d43b1695-d8a8-44f5-ae8b-512a7ecffdb9");


context
*******
As you can setup some predefined parameters in your analysis, it's possible to get these value from the context variable defined in the admin. It is a object, and it comes with follow properties:

    +----------------+--------------------------------------+
    | PROPERTY       |  VALUE                               |
    +================+======================================+
    | environment    | All environment variables            |
    +----------------+--------------------------------------+
    | token          | Token of the analysis                |
    +----------------+--------------------------------------+
    | .log(/msg/)    | Print a message to the admin console |
    +----------------+--------------------------------------+

scope
*****
Every time an action triggers a script, the variable **scope** will be generated. This scope will bring all others variables generated at the same time by the same event. For example, if you submit a `form <http://docs.tago.io/en/latest/dashboard.html#widget-form>`_, together with the variable that the script is reading, the scope will return a list of all values/variable input in that form. This allows you to manipulate data in real time, and more easily the new values inserted in your bucket.

Runtime Timeout
***************
Tago Analysis has a mechanism that prevents scripts from being locked in their executions by applying a timeout of 30 seconds. It means that if a script takes more than 30 seconds to be completed, Tago will abort it, and the script will not be completed.

This limitation doesn't apply when running the analyze from your own machine.  Check the information below to learn how to run scripts from an external server (e.g. from your own computer).

Running in your machine
***********************
You always have the option to run your script from your own machine or from Tago server without any technical difference. When running the script from your machine, you will need to install all the packages used by your analysis by using the command  **npm install mypackage**.

Be sure to set your analysis configuration with the option to run the script from "external". 
And finally, get the analysis token from the same configuration screen, and put it on the second parameter when calling  **new Analysis**. Check out this example:

myanalysis.listening(listener, "d43b1695-d8a8-44f5-ae8b-512a7ecffdb9")


Services
********
We provide some functions that can greatly help your application. When creating a analysis, you are can use Tago services on your own, just make sure you understand the policies and cost associate with the usage.

When setting up a service, you need to pass an analysis-token. For convenience, the context returns a property token that you can use to setup a service object.


.. code-block:: java

    Analysis myanalysis = new Analysis("d43b1695-d8a8-44f5-ae8b-512a7ecffdb9");

    myanalysis.sms.send(data);

sms
===
You can configure the system to send SMS directly from your analysis to yourself or your customers. Another option is to use the Actions to send SMS.

Some costs may occur when using the SMS service, which varies based on the country of operation. Check pricing, terms of use, and your plan before using the SMS service.

.send
-----
Whenever you need to send a sms, use .send function.

| **Syntax**
| *.send(/to/, /message/)*
|
| **Arguments**
| *to(string) A string with a phone number. If not sending to the USA, you have to add the country code, (+55) for Brazil, for example.*
| *message(string) message to be sent. Use "\n" to breakline. (optional)*
|
| **Returns**

.. code-block:: java

    Result(){
      public Boolean status;
      public String message;
      public Object result;
    }


.. code-block:: java

    Analysis myanalysis = new Analysis("d43b1695-d8a8-44f5-ae8b-512a7ecffdb9");

    Object data = new Object() {
        public String to = "2693856214";
        public String message = "'Hi! This is a sms example sent from Tago. \\nWith a breakline in the sms message.";
    };
    Result res = myanalysis.sms.send(data);


email
=====
Email service allows you to send e-mail through your analysis.  Cost may occur when using the e-mail service.

.send
-----
Whenever you need to send an email, use .send function.

| **Syntax**
| *.send(/to/, /subject/, /message/, /from/)*
|
| **Arguments**
| *to(string) E-mail address which will receive the email.*
| *subject(string) Subject of the email;*
| *message(string) message to be sent.*
| *from(string) E-mail address for the receiver to reply. Default is tago@tago.io (optional);*
|
| **Returns**

.. code-block:: java

    Result(){
      public Boolean status;
      public String message;
      public Object result;
    }


.. code-block:: java

    Analysis myanalysis = new Analysis("d43b1695-d8a8-44f5-ae8b-512a7ecffdb9");

    Object data = new Object() {
        public String to = "myuser@gmail.com";
        public String subject = "E-mail example";
        public String message = "Hi! This is an email example.";
        public String to = "me@gmail.com";
    };
    Result res = myanalysis.email.send(data);

