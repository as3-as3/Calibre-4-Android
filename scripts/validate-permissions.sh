#!/bin/bash
# scripts/validate-permissions.sh

echo "Validating Android permissions..."

DANGEROUS_PERMISSIONS=(
    "READ_EXTERNAL_STORAGE"
    "WRITE_EXTERNAL_STORAGE"
    "READ_MEDIA_AUDIO"
    "READ_MEDIA_IMAGES"
    "READ_MEDIA_VIDEO"
    "ACCESS_FINE_LOCATION"
    "ACCESS_COARSE_LOCATION"
    "BLUETOOTH"
    "BLUETOOTH_ADMIN"
    "POST_NOTIFICATIONS"
)

MANIFEST="app/src/main/AndroidManifest.xml"

for permission in "${DANGEROUS_PERMISSIONS[@]}"; do
    if grep -q "android.permission.$permission" app/src/main/java/**/*.kt 2>/dev/null; then
        if ! grep -q "android.permission.$permission" "$MANIFEST"; then
            echo "ERROR: Permission $permission used in code but not declared in manifest!"
            exit 1
        fi
    fi
done

echo "✓ All permissions validated successfully"
