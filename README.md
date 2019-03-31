# showkokhon-scrapper
> Web scrapping and selenium vodoo magic.

Scraps movie show times from Star Cineplex website and writes them to a
`JSON` file.


## Dev
First download the `gecko` web driver [from here](https://github.com/mozilla/geckodriver/releases) for your OS.. Then,

### The hard way
- Install gradle
- Clone the repo
- `cd` into the directory
- Run the following into your command line.

```bash
gradle build
```

### The easy way
Use an IDE. Intellij IDEA preferable.

## Dependencies
- `jsoup`
- `selenium-java`
- `selenium-firefox-driver`
- `jackson-databind`

## Sample JSON output
```json
[ {
  "locationName" : "Bashundhara Shopping Mall, Panthapath",
  "dates" : [ {
    "dateString" : "Thursday, April 4, 2019",
    "moviesOnThisDate" : [ {
      "name" : "Live from Dhaka (2D)",
      "showTimes" : [ "11:10 AM", "04:45 PM" ]
    }, {
      "name" : "Jodi Ekdin (2D)",
      "showTimes" : [ "10:50 AM", "01:40 PM", "04:30 PM", "07:20 PM" ]
    }, .....
 {
  "locationName" : "Shimanto Shambhar, Dhanmondi 2",
  "dates" : [ {
    "dateString" : "Thursday, April 4, 2019",
    "moviesOnThisDate" : [ {
      "name" : "Live from Dhaka (2D)",
      "showTimes" : [ "11:10 AM", "04:45 PM" ]
    }, {
      "name" : "Jodi Ekdin (2D)",
      "showTimes" : [ "10:50 AM", "01:40 PM", "04:30 PM", "07:20 PM" ]
    }, {
      "name" : "Captain Marvel (3D)",
      "showTimes" : [ "10:50 AM", "01:40 PM", "04:00 PM", "04:30 PM", "07:00 PM", "07:30 PM" ]
    }, {
      "name" : "Dumbo (3D)",
      "showTimes" : [ "11:00 AM", "11:30 AM", "01:35 PM", "02:10 PM", "04:10 PM", "04:50 PM", "06:50 PM", "07:15 PM" ]
    }, {
      "name" : "Fagun Haway (2D)",
      "showTimes" : [ "02:00 PM" ]
    }, ......... {
      "name" : "How to Train Your Dragon 3: The Hidden World (3D)",
      "showTimes" : [ "11:20 AM", "01:50 PM" ]
    } ]
  } ]
} ]
```


## Known Issues
Star cineplex website can get unresponsive at times and that'll make your web driver spit up a lot of `stackTrace`. Simply try again.


## License
MIT