LOCAL_PATH := $(call my-dir)

include $(CLEAR_VARS)
LOCAL_MODULE := RemovePkgs
LOCAL_MODULE_CLASS := APPS
LOCAL_MODULE_TAGS := optional

LOCAL_OVERRIDES_PACKAGES := \
    CalculatorGooglePrebuilt_85006267 \
    CalendarGooglePrebuilt \
    DevicePolicyPrebuilt-v10572820 \
    Maps \
    MlkitBarcodeUIPrebuilt \
    Photos \
    PrebuiltGmail \
    PrebuiltDeskClockGoogle_76042511 \
    SafetyHubPrebuilt \
    ScribePrebuilt_v8.7.880674799 \
    SearchSelectorPrebuilt \
    SwitchAccessPrebuilt_1.17.0.877181440 \
    VisionBarcodePrebuilt \
    Velvet \
    PixelLiveWallpaperPrebuilt-26000013 \
    WeatherPixelPrebuilt_10006113 \
    WellbeingPrebuilt \
    TurboPrebuilt \
    WallpaperEmojiPrebuilt-foldable-wallpaper \
    MagicPortraitWallpapers 

LOCAL_UNINSTALLABLE_MODULE := true
LOCAL_CERTIFICATE := platform
LOCAL_SRC_FILES := /dev/null
include $(BUILD_PREBUILT)