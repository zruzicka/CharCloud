# CharCloud #

[![Build status](https://api.travis-ci.org/zruzicka/CharCloud.svg?branch=master)](https://travis-ci.org/zruzicka/CharCloud/)

CharCloud is simple demo project which generates HTML content based on provided plain text input. 
Generated HTML uses style in a way that every letter's height is inversely proportional to letter frequency in the particular content.

## Known available approaches ##

### Generated HTML with individual letters styling ###

* It's easy and quick for implementation.
* Fits only for small inputs - larger input consumes HW resources for HTML parsing.
* Highly inefficient data bandwidth - serialized output is too large if we consider ratio of serialized styling to real content amount.

### Generated both HTML and dedicated font ###

* Font could be generated and dedicated for the specific input.
* Efficient bandwidth usage.
* Requires suitaible font format selection, font structure knowledge and dynamic font generating during input processing.

## CharCloud ##

* CharCloud is implemented as Maven project for Java platform.
* Generated output is HTML and CSS.
* Individual letters styling approach was chosen for this demo.

## Initial setup and launch ##

* There is no external dependencies except Maven and Java.
* Produced output is generated into root folder.
* Tests and generator with sample scenario can be started via 'mvn test'.