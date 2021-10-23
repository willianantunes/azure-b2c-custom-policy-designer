import React from "react"
import * as S from "./styled"
import Link from "next/link"

const menuLinkSetup = [
  {
    label: "Home",
    href: "/",
    testId: "link-home",
  },
  {
    label: "About",
    href: "/about",
    testId: "link-about",
  },
]

const AllSiteLinks = () => {
  return menuLinkSetup.map(({ label, href, testId, onClick, external }) => {
    const props = { key: href, "data-testid": testId, href, onClick }

    if (external === true) {
      props.rel = "noreferrer noopener"
      props.target = "_blank"
    }

    // eslint-disable-next-line react/jsx-key
    return <Link {...props}>{label}</Link>
  })
}

const Header = () => {
  const name = "Azure B2C Custom Policy Designer"

  return (
    <S.MainWrapper>
      <S.BrandWrapper>
        <S.Title>{name}</S.Title>
      </S.BrandWrapper>
      <S.LinksWrapper>
        <S.SiteLinksWrapper data-testid="site-links-wrapper">
          <AllSiteLinks />
        </S.SiteLinksWrapper>
      </S.LinksWrapper>
    </S.MainWrapper>
  )
}

export default Header
