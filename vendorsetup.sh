if [ ! -d "vendor/xiaomi/violet" ]; then
    git clone --depth=1 https://github.com/Justadeayo/platform_vendor_xiaomi_violet.git -b 16 vendor/xiaomi/violet
fi

if [ ! -d "kernel/xiaomi/violet" ]; then
    git clone --depth=1 https://github.com/Justadeayo/android_kernel_xiaomi_violet.git -b sixteen kernel/xiaomi/violet
fi

if [ ! -d "vendor/xiaomi-firmware/violet" ]; then
    git clone --depth=1 https://gitlab.com/CuriousNom/chaste_vendor-xiaomi-firmware-violet.git -b 14.0 vendor/xiaomi-firmware/violet
fi

if [ ! -d "hardware/xiaomi" ]; then
    git clone https://github.com/Evolution-X-Devices/hardware_xiaomi -b bka-no-dolby hardware/xiaomi
    rm -rf hardware/xiaomi/packages/DSPVolumeSynchronizer
fi

if [ ! -d "hardware/dolby" ]; then
    git clone https://github.com/adi8900/hardware_dolby -b lunaris hardware/dolby
fi
