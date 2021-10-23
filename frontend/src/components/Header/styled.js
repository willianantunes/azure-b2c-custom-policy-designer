import styled from "styled-components"
import { Box, Container, Typography } from "@mui/material"

export const MainWrapper = styled(Container).attrs({
  component: "header",
})`
  padding-top: ${props => props.theme.spacing(4)}px;
  padding-bottom: ${props => props.theme.spacing(4)}px;
`

export const BrandWrapper = styled(Box)``

export const Title = styled(Typography).attrs({
  component: "h1",
  variant: "h6",
  align: "center",
})``

export const LinksWrapper = styled(Box).attrs({
  component: "nav",
})``

export const SiteLinksWrapper = styled(Box)`
  display: flex;
  justify-content: center;
  gap: 30px;
  & a {
    text-transform: uppercase;
  }
  padding-top: 12px;
  ${props => props.theme.breakpoints.up("sm")} {
    gap: 50px;
  }
`
