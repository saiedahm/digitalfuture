import { useState } from "react";
import { ArrowUpRight, Bell, ChevronRight, Crown, Globe2, Grid2X2, Home, Menu, Settings, UserRound, X } from "lucide-react";

type Platform = {
  id: string;
  name: string;
  subtitle: string;
  description: string;
  url: string;
  logo: string;
  theme: "blue" | "gold" | "ivory";
};

const platforms: Platform[] = [
  {
    id: "sakan",
    name: "SAKAN EUROPE",
    subtitle: "REAL ESTATE",
    description: "Discover homes and property opportunities across Europe.",
    url: "https://www.sakanapp.net/",
    logo: "/logos/sakan-europe.png",
    theme: "blue"
  },
  {
    id: "nexora",
    name: "NEXORA",
    subtitle: "EXCLUSIVE LIFESTYLE",
    description: "An elevated destination for an exclusive lifestyle.",
    url: "https://www.nexoraonline.de/",
    logo: "/logos/nexora.png",
    theme: "gold"
  },
  {
    id: "helpme",
    name: "HELPME",
    subtitle: "MODERN LUXURY",
    description: "Explore a curated world of modern luxury and style.",
    url: "https://www.helpmey.net/",
    logo: "/logos/helpme.png",
    theme: "ivory"
  },
  {
    id: "digital",
    name: "DIGITAL FUTURE",
    subtitle: "THE DIGITAL PLATFORM",
    description: "Your central gateway to the Digital Future ecosystem.",
    url: "https://www.digital-future.ai/",
    logo: "/logos/digital-future.png",
    theme: "blue"
  }
];

export default function App() {
  const [activeTab, setActiveTab] = useState("Home");
  const [menuOpen, setMenuOpen] = useState(false);
  const [selected, setSelected] = useState<Platform | null>(null);

  const openPlatform = (platform: Platform) => {
    setSelected(platform);
  };

  return (
    <main className="app-shell">
      <div className="ambient ambient-one" />
      <div className="ambient ambient-two" />
      <header className="topbar">
        <button className="icon-button" aria-label="Open menu" onClick={() => setMenuOpen(true)}><Menu size={22} /></button>
        <a className="brand-lockup" href="#home" onClick={() => setActiveTab("Home")}>
          <Crown className="brand-crown" size={27} strokeWidth={1.6} />
          <span>DIGITAL FUTURE</span>
          <small>ONE PLATFORM · FOUR WORLDS</small>
        </a>
        <button className="icon-button" aria-label="Notifications" onClick={() => setSelected({
          id: "notice", name: "Welcome", subtitle: "DIGITAL FUTURE", description: "Welcome to your central destination for SAKAN, NEXORA, HELPME and Digital Future.", url: "https://www.digital-future.ai/", logo: "/logos/digital-future.png", theme: "blue"
        })}><Bell size={21} /></button>
      </header>

      <section className="hero" id="home">
        <div className="hero-orbit"><Globe2 size={190} strokeWidth={0.65} /></div>
        <div className="hero-copy">
          <span className="eyebrow">A WORLD OF POSSIBILITIES</span>
          <h1>Your future.<br /><em>One destination.</em></h1>
          <p>Four distinctive worlds. One seamless digital experience.</p>
        </div>
        <div className="hero-rule"><span /><i>✦</i><span /></div>
      </section>

      <section className="platform-section" id="platforms">
        <div className="section-heading">
          <div><span className="eyebrow">EXPLORE THE ECOSYSTEM</span><h2>Our Platforms</h2></div>
          <span className="count">04 WORLDS</span>
        </div>
        <div className="platform-grid">
          {platforms.map((platform, index) => (
            <button className={`platform-card ${platform.theme}`} key={platform.id} onClick={() => openPlatform(platform)}>
              <span className="card-index">0{index + 1}</span>
              <span className="logo-frame">
                <img src={platform.logo} alt={`${platform.name} official logo`} onError={(event) => { event.currentTarget.style.visibility = "hidden"; }} />
                <span className="logo-fallback">{platform.id === "digital" ? <Crown size={38} /> : platform.name}</span>
              </span>
              <span className="platform-title">{platform.name}</span>
              <span className="platform-subtitle">{platform.subtitle}</span>
              <span className="card-bottom"><span>Discover platform</span><span className="round-arrow"><ArrowUpRight size={17} /></span></span>
            </button>
          ))}
        </div>
      </section>

      <section className="signature">
        <div className="signature-mark"><Crown size={24} /></div>
        <span className="eyebrow">DESIGNED FOR WHAT'S NEXT</span>
        <h2>One platform.<br /><em>Infinite possibilities.</em></h2>
        <p>A considered digital experience connecting the worlds you care about.</p>
      </section>

      <footer className="footer">
        <Crown size={19} />
        <span>DIGITAL FUTURE</span>
        <small>YOUR FUTURE, CONNECTED.</small>
        <a href="https://www.digital-future.ai/" target="_blank" rel="noreferrer">Official website <ArrowUpRight size={13} /></a>
      </footer>

      <nav className="bottom-nav" aria-label="Main navigation">
        {[
          { label: "Home", icon: Home },
          { label: "Platforms", icon: Grid2X2 },
          { label: "Account", icon: UserRound },
          { label: "Settings", icon: Settings }
        ].map(({ label, icon: Icon }) => (
          <button key={label} className={activeTab === label ? "nav-item active" : "nav-item"} onClick={() => {
            setActiveTab(label);
            if (label === "Platforms") document.getElementById("platforms")?.scrollIntoView({ behavior: "smooth" });
            else if (label === "Home") window.scrollTo({ top: 0, behavior: "smooth" });
            else setSelected({ id: label, name: label, subtitle: "DIGITAL FUTURE", description: label === "Account" ? "Account features can be connected after the shared sign-in service is configured." : "App preferences and language settings will be available here.", url: "https://www.digital-future.ai/", logo: "/logos/digital-future.png", theme: "blue" });
          }}><Icon size={20} strokeWidth={1.7} /><span>{label}</span></button>
        ))}
      </nav>

      {menuOpen && <div className="overlay" onClick={() => setMenuOpen(false)}>
        <aside className="side-panel" onClick={(event) => event.stopPropagation()}>
          <button className="close-button" onClick={() => setMenuOpen(false)} aria-label="Close menu"><X /></button>
          <Crown className="panel-crown" size={34} />
          <h2>Digital Future</h2><p className="panel-caption">ONE PLATFORM · FOUR WORLDS</p>
          {platforms.map((platform) => <button className="menu-link" key={platform.id} onClick={() => { setMenuOpen(false); openPlatform(platform); }}>{platform.name}<ChevronRight size={17} /></button>)}
          <a className="panel-website" href="https://www.digital-future.ai/" target="_blank" rel="noreferrer">Visit official website <ArrowUpRight size={15} /></a>
        </aside>
      </div>}

      {selected && <div className="overlay" onClick={() => setSelected(null)}>
        <section className="detail-modal" onClick={(event) => event.stopPropagation()}>
          <button className="close-button" onClick={() => setSelected(null)} aria-label="Close details"><X /></button>
          <div className="modal-logo"><img src={selected.logo} alt="" onError={(event) => { event.currentTarget.style.display = "none"; }} /><Crown size={36} /></div>
          <span className="eyebrow">{selected.subtitle}</span>
          <h2>{selected.name}</h2><p>{selected.description}</p>
          <a className="launch-button" href={selected.url} target="_blank" rel="noreferrer">Open platform <ArrowUpRight size={17} /></a>
          <small className="secure-note">You are opening the official platform website.</small>
        </section>
      </div>}
    </main>
  );
}