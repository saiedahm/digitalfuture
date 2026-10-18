import { useState } from "react";
import { ArrowUpRight, Bell, ChevronRight, Crown, Grid2X2, Home, Menu, Settings, UserRound, X } from "lucide-react";

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
    subtitle: "DIGITAL SERVICES",
    description: "Your central destination for the digital age.",
    url: "https://www.digital-future.ai/",
    logo: "/logos/digital-future.png",
    theme: "blue"
  }
];

export default function App() {
  const [activeTab, setActiveTab] = useState("Home");
  const [menuOpen, setMenuOpen] = useState(false);
  const [selected, setSelected] = useState<Platform | null>(null);

  const openPlatform = (platform: Platform) => setSelected(platform);

  return (
    <main className="app-shell compact-home">
      <div className="ambient ambient-one" />
      <div className="ambient ambient-two" />

      <header className="topbar">
        <button className="icon-button" aria-label="Open menu" onClick={() => setMenuOpen(true)}><Menu size={21} /></button>
        <a className="brand-lockup" href="#home" onClick={() => setActiveTab("Home")}>
          <Crown className="brand-crown" size={27} strokeWidth={1.6} />
          <span>DIGITAL FUTURE</span>
          <small>THE DIGITAL FUTURE IS HERE</small>
        </a>
        <button className="icon-button" aria-label="Notifications" onClick={() => setSelected({
          id: "welcome",
          name: "DIGITAL FUTURE",
          subtitle: "THE DIGITAL FUTURE IS HERE",
          description: "Welcome to Digital Future.",
          url: "https://www.digital-future.ai/",
          logo: "/logos/digital-future.png",
          theme: "blue"
        })}><Bell size={20} /></button>
      </header>

      <section className="platform-section compact-platforms" id="home" aria-label="Digital Future services">
        <div className="platform-grid">
          {platforms.map((platform) => (
            <button className={`platform-card ${platform.theme}`} key={platform.id} onClick={() => openPlatform(platform)}>
              <span className="logo-frame">
                <img
                  src={platform.logo}
                  alt={`${platform.name} official logo`}
                  onError={(event) => { event.currentTarget.style.visibility = "hidden"; }}
                />
                <span className="logo-fallback">{platform.name}</span>
              </span>
              <span className="platform-title">{platform.name}</span>
              <span className="platform-subtitle">{platform.subtitle}</span>
              <span className="card-bottom"><span>Open</span><span className="round-arrow"><ArrowUpRight size={16} /></span></span>
            </button>
          ))}
        </div>
      </section>

      <nav className="bottom-nav" aria-label="Main navigation">
        {[
          { label: "Home", icon: Home },
          { label: "Services", icon: Grid2X2 },
          { label: "Account", icon: UserRound },
          { label: "Settings", icon: Settings }
        ].map(({ label, icon: Icon }) => (
          <button key={label} className={activeTab === label ? "nav-item active" : "nav-item"} onClick={() => {
            setActiveTab(label);
            if (label === "Home" || label === "Services") {
              document.getElementById("home")?.scrollIntoView({ behavior: "smooth", block: "start" });
            } else {
              setSelected({
                id: label,
                name: label,
                subtitle: "DIGITAL FUTURE",
                description: label === "Account"
                  ? "Account features will be available when account services are connected."
                  : "App preferences will be available here.",
                url: "https://www.digital-future.ai/",
                logo: "/logos/digital-future.png",
                theme: "blue"
              });
            }
          }}><Icon size={19} strokeWidth={1.7} /><span>{label}</span></button>
        ))}
      </nav>

      {menuOpen && <div className="overlay" onClick={() => setMenuOpen(false)}>
        <aside className="side-panel" onClick={(event) => event.stopPropagation()}>
          <button className="close-button" onClick={() => setMenuOpen(false)} aria-label="Close menu"><X /></button>
          <Crown className="panel-crown" size={34} />
          <h2>Digital Future</h2>
          <p className="panel-caption">THE DIGITAL FUTURE IS HERE</p>
          {platforms.map((platform) => (
            <button className="menu-link" key={platform.id} onClick={() => { setMenuOpen(false); openPlatform(platform); }}>
              {platform.name}<ChevronRight size={17} />
            </button>
          ))}
          <a className="panel-website" href="https://www.digital-future.ai/" target="_blank" rel="noreferrer">Official website <ArrowUpRight size={15} /></a>
        </aside>
      </div>}

      {selected && <div className="overlay" onClick={() => setSelected(null)}>
        <section className="detail-modal" onClick={(event) => event.stopPropagation()}>
          <button className="close-button" onClick={() => setSelected(null)} aria-label="Close details"><X /></button>
          <div className="modal-logo"><img src={selected.logo} alt="" onError={(event) => { event.currentTarget.style.display = "none"; }} /><Crown size={32} /></div>
          <span className="eyebrow">{selected.subtitle}</span>
          <h2>{selected.name}</h2>
          <p>{selected.description}</p>
          <a className="launch-button" href={selected.url} target="_blank" rel="noreferrer">Open website <ArrowUpRight size={17} /></a>
          <small className="secure-note">You are opening the official website.</small>
        </section>
      </div>}
    </main>
  );
}
