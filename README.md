# Tago - Node.JS Lib
Official Java lib for Tago

# Code Status
Work in progress

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
**.delete("String");**

```java

device.delete("put_the_data_id_here");
// or
device.delete(); // Without the ID the last record will be deleted
```

### Update Data

```java
//Not implemented yet
```

### Listening new data by Socket

```java
//Not implemented yet
```

# License
Tago lib client for Node.js is released under the [Copyright License](https://github.com/tago-io/tago-nodejs/blob/master/LICENSE.md).
