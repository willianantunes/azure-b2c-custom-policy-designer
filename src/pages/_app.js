import * as React from "react"
import PropTypes from "prop-types"
import Head from "next/head"
import { ThemeProvider as StyledComponentsThemeProvider } from "styled-components"
import { StyledEngineProvider, ThemeProvider as MuiThemeProvider } from "@mui/material/styles"
import CssBaseline from "@mui/material/CssBaseline"
import theme from "../styles/theme"

export default function MyApp(props) {
  const { Component, pageProps } = props
  return (
    <>
      <Head>
        <title>Next App</title>
        <link href="/favicon.ico" rel="icon" />
        <meta content="minimum-scale=1, initial-scale=1, width=device-width" name="viewport" />
      </Head>
      <StyledEngineProvider injectFirst>
        <MuiThemeProvider theme={theme}>
          <StyledComponentsThemeProvider theme={theme}>
            <CssBaseline />
            <Component {...pageProps} />
          </StyledComponentsThemeProvider>
        </MuiThemeProvider>
      </StyledEngineProvider>
    </>
  )
}

MyApp.propTypes = {
  Component: PropTypes.elementType.isRequired,
  pageProps: PropTypes.object.isRequired,
}
