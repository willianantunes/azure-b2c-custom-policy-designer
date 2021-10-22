import React from "react"
import { render } from "@testing-library/react"
import { TopLayout } from "../../src/pages/_app"

// See more details at: https://github.com/willianantunes/willianantunes.com/blob/4381363547be53104be66768651152f05fa25b12/tests/support/test-utils.js

const AllTheProviders = ({ children }) => {
  return <TopLayout>{children}</TopLayout>
}

const customRender = (ui, options) => {
  return render(ui, { wrapper: AllTheProviders, ...options })
}

// Re-export everything
export * from "@testing-library/react"

// Override render method
export { customRender as render }
