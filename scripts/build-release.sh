#!/usr/bin/env bash
set -euo pipefail

ROOT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")/.." && pwd)"
cd "$ROOT_DIR"

if command -v gradle >/dev/null 2>&1; then
  GRADLE_CMD="gradle"
elif [ -x "./gradlew" ]; then
  GRADLE_CMD="./gradlew"
else
  echo "Gradle bulunamadı. Lütfen Java ve Gradle kurulumunu yapın."
  echo "Örnek: brew install gradle   veya   sudo apt install gradle"
  exit 1
fi

echo "[1/2] Projeyi derliyor..."
$GRADLE_CMD buildAll

echo "[2/2] Yayın için JAR dosyaları hazırlandı."
echo "server/build/libs/super-pixel-duegon-server.jar"
echo "client/build/libs/super-pixel-duegon-client.jar"
