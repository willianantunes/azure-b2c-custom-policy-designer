const modules = ["@mui/material", "@mui/system"]
const withTransportModules = require("next-transpile-modules")(modules)

module.exports = withTransportModules({
  reactStrictMode: true,
  webpack: config => {
    config.resolve.alias = {
      ...config.resolve.alias,
      "@mui/styled-engine": "@mui/styled-engine-sc",
    }
    return config
  },
})
