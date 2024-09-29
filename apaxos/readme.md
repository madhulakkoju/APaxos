# Server Configurations

## Overview
This document outlines the configurations for the servers and the View Server responsible for handling transactions.

## Server Details
The following servers have been considered:

| Server Name | Client Name | Port Number |
|-------------|-------------|-------------|
| S1          | A           | 8001        |
| S2          | B           | 8002        |
| S3          | C           | 8003        |
| S4          | D           | 8004        |
| S5          | E           | 8005        |
| View Server |             | 8000        |

## View Server
This is actually not a View Server by definition. This is just a bridge between Input File and servers.

This is responsible for taking the input file and sending the transactions to the respective servers. 

- **View Server Port Number:** 8000
