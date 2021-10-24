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

About Azure AD B2C:

- [Azure AD B2C HTML templates](https://github.com/azure-ad-b2c/html-templates)
- [Customize the user interface with HTML templates in Azure Active Directory B2C](https://docs.microsoft.com/en-us/azure/active-directory-b2c/customize-ui-with-html)
- https://github.com/Azure-Samples/active-directory-b2c-custom-policy-starterpack
- https://github.com/azure-ad-b2c/vscode-extension
- https://github.com/azure-ad-b2c/samples

Interesting ones to build this project in terms of technology I'm using:

- [Next.js with MUI](https://github.com/mui-org/material-ui/tree/master/examples/nextjs)

## Issues

- https://github.com/mui-org/material-ui/issues/27087
- https://github.com/mui-org/material-ui/issues/27846
