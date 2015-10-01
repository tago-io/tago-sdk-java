# Tago - Java Lib
Official Java lib for Tago

# Code Status
[![wercker status](https://app.wercker.com/status/ce550090c4e12182442fb0af7ed7928b/m "wercker status")](https://app.wercker.com/project/bykey/ce550090c4e12182442fb0af7ed7928b)

Work in progress...

# Documentation
## Usage
### Insert Data
**.insert(Data);**

```Java
Device device = new Device("put_your_token_here");

//Data to insert
Data data = new Data();
        data.variable = "API-Teste";
        data.unit = "%";
        data.value = "25";
        data.type = "text";
        data.time = new Date();
        data.location = new Location(40.792673, -98.683232);

device.insert(data);
```

### Find Data
**.find(String, String);**

```java
// You can check documentation to see all options you can use in query.
List<Data> dataList = device.find(Constant.Find.FILTER, Constant.Filter.TYPE);

Integer dataCount = device.count();
```

### Delete Data
**.delete(String);**

```java

device.delete("put_the_data_id_here");
// or
device.delete(); // Without the ID the last record will be deleted
```

### Update Data
**.delete(String, Data);**

```java

device.update("put_tye_data_id_here", data);
// or
device.update(data);
```

### Listening new data by Socket

```java
 // the method device.listening() activates the socket connection that
 // listens to the api value changes
 device.listening();

 // to start listening to the api you need to use the method socket.on
 device.socket.on("data", new Emitter.Listener() {

     @Override
     public void call(Object... result) {
//      The method call will be triggered when the data is changed at the api
//      the result will be the object "result"
     }
 });
```

# License
Tago lib client for Node.js is released under the [Copyright License](https://github.com/tago-io/tago-nodejs/blob/master/LICENSE.md).
