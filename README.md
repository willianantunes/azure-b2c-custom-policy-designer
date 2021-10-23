# Azure B2C Custom Policy Designer

Why has Microsoft never built a UI to deal with [Custom Policy](https://docs.microsoft.com/en-us/azure/active-directory-b2c/custom-policy-overview) so far? I don't know, but I'll do it for free because it hurts my software developer soul 😬.

## Some details and using it

This project is separated in two components:

- [frontend](./frontend): A Next.js project with MUI and styled-components. It represents the UI where you can design your custom policy. Sadly, XML is not well covered in the JavaScript world, which justifies the backend part below.
- [backend](./backend): A Spring Boot application with Spring Web to enable the use of REST APIs. All the jobs related to XML handling are done by this service. 

To start using this project, just execute the command:

    docker-compose up

Then access `http://localhost:3000/`, and you're good to go!

## Project details

More to come soon 👀.

## Links

- [Next.js with MUI](https://github.com/mui-org/material-ui/tree/master/examples/nextjs)

## Issues

- https://github.com/mui-org/material-ui/issues/27087
- https://github.com/mui-org/material-ui/issues/27846
