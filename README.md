# DonutSMP Fake Pay & Scoreboard Mod

Ein Minecraft Fabric Mod für Version 1.21.1, der ein Fake Pay System und ein anpassbares Scoreboard ähnlich wie in DonutSMP implementiert.

## Features

- 🎯 **Anpassbares Scoreboard** mit GUI
- 💰 **Fake Pay System** mit DonutSMP-ähnlichen Chat-Meldungen
- 🖼️ **Echtzeit-Scoreboard Updates** nach Zahlungen
- ⚙️ **Konfigurierbare Position, Größe und Text**

## Installation

1. **Fabric Loader** herunterladen und installieren (mindestens 0.16.0)
2. **Fabric API** herunterladen und in den `mods` Ordner legen
3. Diese Mod in den `mods` Ordner legen
4. Minecraft starten

## Verwendung

### Scoreboard konfigurieren
- Drücke **P** im Spiel, um das Konfigurations-GUI zu öffnen
- Ändere den Titel und die Zeilen nach deinen Wünschen
- Klicke "Save" um die Änderungen zu speichern

### Fake Pay verwenden
```
/pay <spielername> <betrag>
```

Beispiel:
```
/pay Steve 100
```

Dies sendet die Nachricht: `[PAY] Votre joueur a payé Steve 100€` und aktualisiert das Scoreboard automatisch.

## Konfigurationsdatei

Die Konfiguration wird gespeichert unter:
- Windows: `%APPDATA%\.minecraft\config\donutsmp_scoreboard.json`
- macOS: `~/Library/Application Support/minecraft/config/donutsmp_scoreboard.json`
- Linux: `~/.minecraft/config/donutsmp_scoreboard.json`

Beispiel:
```json
{
  "title": "§6SCOREBOARD",
  "lines": [
    "§eJoueur1: §6100€",
    "§eJoueur2: §650€",
    "§eJoueur3: §625€"
  ],
  "enabled": true,
  "x": 10,
  "y": 10,
  "scale": 1.0
}
```

## Farb-Codes

- `§6` - Gold
- `§e` - Gelb
- `§f` - Weiß
- `§0` - Schwarz
- `§1` - Dunkelblau
- `§4` - Dunkelrot

[Vollständige Liste Minecraft Farbcodes](https://wiki.vg/Text_component#Color)

## Tastenkombinationen

- **P** - Öffne Scoreboard-Konfiguration
- **ESC** - Schließe Konfiguration

## Kompatibilität

- **Minecraft Version**: 1.21.1
- **Loader**: Fabric 0.16.0+
- **Java**: 21+

## License

MIT License - Frei verwendbar und modifizierbar

## Support

Erstelle ein Issue auf GitHub für Bugs oder Feature-Anfragen.
