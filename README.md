[![VEFA-VALIDATOR Master Build](https://github.com/OxalisCommunity/vefa-validator/workflows/VEFA-VALIDATOR%20Master%20Build/badge.svg)](https://github.com/OxalisCommunity/vefa-validator/actions?query=workflow%3A%22VEFA-VALIDATOR%20Master%20Build%22)
[![Maven Central](https://img.shields.io/maven-central/v/network.oxalis/vefa-validator.svg)](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22network.oxalis%22%20AND%20a%3A%22vefa-validator%22)

## VEFA Validator 3.x - Upcoming
If you want validation support for any specific types of documents or have issues related to specific types of documents, please make raise a ticket (feature/bug) request at: https://github.com/OxalisCommunity/vefa-validator/issues 

## Features

* **Very easy to use.**
* Supports **rendering documents**.
* Very **low footprint** in your code.
* **Pooling** of resources.
* Supports **different lifecycles** of validation artifacts.
* **[Configurable](https://github.com/OxalisCommunity/vefa-validator/blob/main/doc/configurations.md)** to fit multiple sizes.


## Getting started

Artifacts are published to maven repository

### 1. Start validating

```java
// Create a new validator using validation artifacts from DFØ.
Validator validator = ValidatorBuilder.newValidator().build();

// Validate business document.
Validation validation = validator.validate(Paths.get("/path/to/document.xml"));

// Print result of validation.
System.out.println(validation.getReport().getFlag());
```

Create the validator once and reuse it – it is expensive to initialize.


---
## This repository is a mirror of project maintained by [anskaffelser](https://github.com/anskaffelser/vefa-validator/)

---
