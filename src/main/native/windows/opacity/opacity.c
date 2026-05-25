/*
 * opacity.c
 * @author          woz07
 * @description     Simple C program to change opacity of windows for windows operating systems only.
 *                  This program expects you to pass through an argument of type float for opacity.
 */

#include<windows.h>

void opacity(HWND hwnd, float opacity) {
    LONG style = GetWindowLong(hwnd, GWL_EXSTYLE);
    SetWindowLong(hwnd, GWL_EXSTYLE, style | WS_EX_LAYERED);
    SetLayeredWindowAttributes(hwnd, 0, (BYTE)(opacity * 255), LWA_ALPHA);
}

int main(int argc, char *argv[]) {
    //Sleep(250);
    HWND hwnd = FindWindow(NULL, "datapeek");
    if (hwnd) {
        opacity(hwnd, atof(argv[1]));
        return 0;
    }
    return 1;
}