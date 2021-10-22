import React from "react"
import * as S from "./styled"

const Footer = () => {
  const author = "Willian Antunes"
  const projectLicense = "https://github.com/willianantunes/azure-b2c-custom-policy-designer/blob/main/LICENSE"
  const currentYear = new Date().getFullYear()

  return (
    <S.MainWrapper>
      <S.SmallDetailsWrapper data-testid="small-details-wrapper">
        © {currentYear} {author.name}
        <S.CustomLink href={projectLicense}>Terms & Conditions</S.CustomLink>
      </S.SmallDetailsWrapper>
    </S.MainWrapper>
  )
}

export default Footer
