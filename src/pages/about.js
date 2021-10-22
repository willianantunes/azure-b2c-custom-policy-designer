import * as React from "react"
import Container from "@mui/material/Container"
import Typography from "@mui/material/Typography"
import Box from "@mui/material/Box"
import Button from "@mui/material/Button"
import * as S from "../components/Header/styled"
import Link from "next/link"
import Layout from "../components/Layout"

export default function About() {
  return (
    <Layout>
      <Box sx={{ p: 2, border: "1px dashed grey" }}>
        <Typography variant="h4" component="h1" gutterBottom>
          Next.js v5 example
        </Typography>
        <Link href="/" passHref>
          <Button variant="contained" noLinkStyle>
            Go to the main page
          </Button>
        </Link>
      </Box>
    </Layout>
  )

  // return (
  //   <Container maxWidth="sm">
  //     <Box sx={{ p: 2, border: "1px dashed grey" }}>
  //       <Typography variant="h4" component="h1" gutterBottom>
  //         Next.js v5 example
  //       </Typography>
  //       <Button variant="contained" component={Link} noLinkStyle href="/">
  //         Go to the main page
  //       </Button>
  //     </Box>
  //   </Container>
  // )
}
