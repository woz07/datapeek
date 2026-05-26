package io.github.woz07.datapeek.app

import com.formdev.flatlaf.FlatDarkLaf
import com.formdev.flatlaf.FlatLightLaf
import com.formdev.flatlaf.themes.FlatMacDarkLaf
import com.formdev.flatlaf.themes.FlatMacLightLaf
import io.github.woz07.datapeek.data.JsonIoService
import java.util.Objects

fun main() {
    // TODO: Update once fixed %APPDATA%/datapeek
    //     (There is some reference code I can look at in my recent projects, in which I did already implement %APPDATA% storage)
    val config = JsonIoService.read("");

    // Question is, should this app only be limited to Windows users?
    //     (Looking like it should yes, due to opacity.c which only works on windows)
    when (Objects.requireNonNull(config.theme)) {
        "window-light" -> FlatLightLaf.setup()
        "window-dark"  -> FlatDarkLaf.setup()
        "mac-light"    -> FlatMacDarkLaf.setup()
        "mac-dark"     -> FlatMacLightLaf.setup()
    }

    println("Works!")
}

// TODO: Need to continue setting this up
//  - Utilize c program (.exe) for opacity of app
//  - Need to rebuild opacity.c as it has no .exe (prev one not reusable as dif target name)
//  - Need to create folder in "%APPDATA%/datapeek" then add a config.json file which we read/write, if missing we create and populate with default
//  - Not done with macrox yet, need to still use and update SettingScreen so we have setting and Window so we can finally get GUI running
//  - Added ScriptExecutor, but it doesn't do anything as of yet, so need to add functionality. Refer to macrox on how it's done