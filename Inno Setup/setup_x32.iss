; Script generated by the Inno Setup Script Wizard.
; SEE THE DOCUMENTATION FOR DETAILS ON CREATING INNO SETUP SCRIPT FILES!

#define MyAppName "CircuitJS1 Desktop Mod"
#define MyAppVersion "1.2.5"
#define MyAppPublisher "Usevalad Khatkevich"
#define MyAppURL "https://github.com/SEVA77/circuitjs1"
#define MyAppExeName "CircuitSimulator.exe"

[Setup]
; NOTE: The value of AppId uniquely identifies this application.
; Do not use the same AppId value in installers for other applications.
; (To generate a new GUID, click Tools | Generate GUID inside the IDE.)
AppId={{6FBFF2C4-BFD7-49FE-AC28-EF2CCCB6BEAA}
AppName={#MyAppName}
AppVersion={#MyAppVersion}
AppVerName={#MyAppName} {#MyAppVersion}
AppPublisher={#MyAppPublisher}
AppPublisherURL={#MyAppURL}
AppSupportURL={#MyAppURL}
AppUpdatesURL={#MyAppURL}
DefaultDirName={pf}\{#MyAppName}
DefaultGroupName={#MyAppName}
AllowNoIcons=yes
LicenseFile=LICENSE.txt
OutputBaseFilename=CircuitJS1_Desktop_Mod_{#MyAppVersion}_x32_setup
SetupIconFile=icon.ico
Compression=lzma
SolidCompression=yes 
WizardSmallImageFile=1.bmp
WizardImageFile=2.bmp

[Languages]
Name: "english"; MessagesFile: "compiler:Default.isl"
Name: "danish"; MessagesFile: "compiler:Languages\Danish.isl"
Name: "german"; MessagesFile: "compiler:Languages\German.isl"
Name: "polish"; MessagesFile: "compiler:Languages\Polish.isl"
Name: "russian"; MessagesFile: "compiler:Languages\Russian.isl"

[Tasks]
Name: "desktopicon"; Description: "{cm:CreateDesktopIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked
Name: "quicklaunchicon"; Description: "{cm:CreateQuickLaunchIcon}"; GroupDescription: "{cm:AdditionalIcons}"; Flags: unchecked; OnlyBelowVersion: 0,6.1

[Files]
Source: "..\CircuitJS1 Desktop Mod\CircuitSimulator.exe"; DestDir: "{app}"; Flags: ignoreversion
Source: "..\CircuitJS1 Desktop Mod\*"; DestDir: "{app}"; Flags: ignoreversion recursesubdirs createallsubdirs
; NOTE: Don't use "Flags: ignoreversion" on any shared system files

[Icons]
Name: "{group}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"
Name: "{group}\{#MyAppName} (disable GPU)"; Filename: "{app}\{#MyAppExeName}"; Parameters: "--disable-gpu"
Name: "{group}\{cm:UninstallProgram,{#MyAppName}}"; Filename: "{uninstallexe}"
Name: "{commondesktop}\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: desktopicon
Name: "{commondesktop}\{#MyAppName} (disable GPU)"; Filename: "{app}\{#MyAppExeName}"; Parameters: "--disable-gpu"; Tasks: desktopicon
Name: "{userappdata}\Microsoft\Internet Explorer\Quick Launch\{#MyAppName}"; Filename: "{app}\{#MyAppExeName}"; Tasks: quicklaunchicon

[Run]
Filename: "{app}\{#MyAppExeName}"; Description: "{cm:LaunchProgram,{#StringChange(MyAppName, '&', '&&')}}"; Flags: nowait postinstall skipifsilent

