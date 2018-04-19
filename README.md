# MATLAB Module(MATLAB)
This is MATLAB application(server) that solves the system of differential equations and send result to the mobile app via the GW.
Components:
 - udp_module
 - ship model
 
```sh
:8845
```
*package: matlab_module*

# Mobile app(Java)
This is Android application that visualizes the dynamics of ship.
Component:
- ship visualization

```sh
:2245
```
* package: app*

# GW module(Java)
This is GW server.
- server send\reseive info between matlab and App

```sh
:4445
```
* package: gw_module *