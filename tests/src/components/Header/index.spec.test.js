import React from "react"
import Header from "../../../../src/components/Header"
import { screen } from "@testing-library/react"
import { render } from "../../../support/test-utils"

describe("Header component", () => {
  it("has links to pages", async () => {
    // Act
    render(<Header />)
    // Assert
    const testId = "site-links-wrapper"
    const element = await screen.findByTestId(testId)

    const expectedPages = ["/", "/about"]
    const linksElements = element.querySelectorAll(`a`)
    expect(linksElements.length).toBe(expectedPages.length)

    for (const pageLink of expectedPages) {
      const htmlElement = element.querySelector(`a[href='${pageLink}']`)
      expect(htmlElement).toBeInTheDocument()
    }
  })
})
