# Dropwizard & LinkedIn
===

This is a simple example app using dropwizard and linkedin-j to pull basic user info. To get the example working you'll need to get a set of API keys from [LinkedIn] [1]

The sample dropwizard-linkedin.yml file has 3 fields for the

  - Consumer Key
  - Consumer Secret
  - Callback URL

Plug the values you get from linkedin into the key and secret, you should be able to use the default callback url.

## Notation

```<project root>``` - The root directory of the project as checked out through git

All commands will work on *nix without modification, use \ instead of / for Windows.

## Getting started

From the console you can do the following
```
cd <project root>
mvn clean install
java -jar target/dropwizard-linkedin-1.0-SNAPSHOT.jar server dropwizard-linkedin.yml
```

===

### Credits

dropwizard-linkedin uses:

* [dropwizard] - a basic java framework from [coda hale]
* [linkedin-j] - a java wrapper for linkedin apis
* [gary rowe] - how to use sessions in dropwizard came from this post about [openid and dropwizard] [2]


  [dropwizard]: http://dropwizard.codahale.com/
  [coda hale]: http://codahale.com/
  [linkedin-j]: http://code.google.com/p/linkedin-j/
  [1]: https://www.linkedin.com/secure/developer
  [gary rowe]: http://gary-rowe.com/
  [2]: http://gary-rowe.com/agilestack/2012/12/12/dropwizard-with-openid/