# Interactive Brokers Product Parser

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
  * ```BOND``` - Bonds

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
