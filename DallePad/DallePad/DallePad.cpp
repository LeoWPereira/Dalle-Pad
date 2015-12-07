/*
* DallePad.cpp
* DallePad
* Created by Leonardo Winter Pereira on 12.05.2015
* Copyright (c) DallePad. All rights reserved.
*/

#include "DallePad.h"

[System::STAThread] //! leave this as is
auto main(array<System::String^>^ args) -> void
{
	System::Windows::Forms::Application::EnableVisualStyles();
	System::Windows::Forms::Application::SetCompatibleTextRenderingDefault(false);

	DallePad::DallePad mainForm;
	System::Windows::Forms::Application::Run(%mainForm);
}