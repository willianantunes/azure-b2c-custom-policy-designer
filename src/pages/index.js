import * as React from "react"
import { Link as MuiLink, Typography } from "@mui/material"
import Link from "next/link"
import Layout from "../components/Layout"

export default function Index() {
  return (
    <Layout>
      <Typography variant="h4" component="h1" gutterBottom>
        Next.js v5 example
      </Typography>
      <Link href="/about" passHref>
        <MuiLink color="secondary">Go to the about page</MuiLink>
      </Link>
    </Layout>
  )
}
