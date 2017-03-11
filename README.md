# Interactive Brokers Product Parser

[![Build Status](https://travis-ci.org/WojciechZankowski/ib-product-parser.svg?branch=master)](https://travis-ci.org/WojciechZankowski/ib-product-parser)
[![Apache 2](http://img.shields.io/badge/license-Apache%202-red.svg)](http://www.apache.org/licenses/LICENSE-2.0)

It's simple command line application to parse Interactive Brokers products from their site https://www.interactivebrokers.com/en/index.php?f=1563 

## Available commands:

* ```-e ```

Exchanges, use exchanges codes from Interactive Brokers. It's possible to put multiple exchanges at once, just list exchanges after space. 
  
* ```-t ```

Product type, possible inputs:

  * ```STK``` - Stocks
  * ```OPTGRP``` - Options
  * ```FUTGRP``` - Futures
  * ```FOPTGRP``` - FOP
  * ```ETF``` - ETF
  * ```WAR``` - Warrants
  * ```IOPT``` - Structured Products
  * ```SSF``` - SSF
  * ```FX``` - Forex
  * ```CMDTY``` - Metals
  * ```IND``` - Indices
  
* ``` -f ```
  
  Formatter class name, if not provided then SimpleFormatter is being used. 

## Presets:

To simplify download there are made presets:

* All products from all exchanges: ```-e all```
* All products from NA exchanges: ```-e allna```
* All products from EU exchanges: ```-e alleu```
* All products from ASIA exchanges: ```-e allasia```

## Examples:

* All stock products from NA exchanges:

```java -jar ibpp.jar -e allna -t STK```

* All stock products from all exchanges:

```java -jar ibpp.jar -e all -t STK```

* All future products from OneChicago exchange:

```java -jar ibpp.jar -e one -t FUTGRP```

* All stock products from NA exchanges with SimpleFormatter:

```java -jar ibpp.jar -e allna -t STK -f pl.zankowski.ibpp.formatter.SimpleFormatter```

## Output format

File is formatted as below:

``` exchange;IB symbol;description;security type;currency;ISIN ```
