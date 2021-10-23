import { red } from "@mui/material/colors"
import { createTheme } from "@mui/material"

// https://mui.com/customization/default-theme/#explore
// https://mui.com/customization/theming/
const theme = createTheme({
  palette: {
    mode: "light",
    // https://mui.com/customization/color/#picking-colors
    primary: {
      light: "#757ce8",
      main: "#3f50b5",
      dark: "#002884",
      contrastText: "#fff",
    },
    secondary: {
      light: "#ff7961",
      main: "#f44336",
      dark: "#ba000d",
      contrastText: "#000",
    },
    error: {
      main: red.A400,
    },
  },
  spacing: [0, 4, 8, 16, 32, 64],
  breakpoints: {
    values: {
      // https://material-ui.com/customization/breakpoints/
      // Standard: xs: 0, sm: 600, md: 960, lg: 1280, xl: 1920
      xs: 0,
      sm: 400,
      md: 768,
      lg: 998,
      xl: 1280,
    },
  },
})

export default theme
