/*
* DallePad.h
* DallePad
* Created by Leonardo Winter Pereira on 12.05.2015
* Copyright (c) DallePad. All rights reserved.
*/

#pragma once

namespace DallePad
{
	using namespace System;
	using namespace System::ComponentModel;
	using namespace System::Collections;
	using namespace System::Windows::Forms;
	using namespace System::Data;
	using namespace System::Drawing;

	/// <summary>
	/// Summary for DallePad
	/// </summary>
	public ref class DallePad : public System::Windows::Forms::Form
	{
	public:
		DallePad()
		{
			InitializeComponent();
			//
			//TODO: Add the constructor code here
			//
		}

	protected:
		/// <summary>
		/// Clean up any resources being used.
		/// </summary>
		~DallePad()
		{
			if (components)
				delete components;
		}

	private:
		/// <summary>
		/// Required designer variable.
		/// </summary>
		System::ComponentModel::Container ^components;
	
		System::Windows::Forms::MenuStrip^  menu;

		System::Windows::Forms::ToolStripMenuItem^  fileMenu;
		System::Windows::Forms::ToolStripMenuItem^  editMenu;
		System::Windows::Forms::ToolStripMenuItem^  createMenu;
		System::Windows::Forms::ToolStripMenuItem^  viewMenu;
		System::Windows::Forms::ToolStripMenuItem^  optionMenu;
		System::Windows::Forms::ToolStripMenuItem^  helpMenu;


#pragma region Windows Form Designer generated code
		/// <summary>
		/// Required method for Designer support - do not modify
		/// the contents of this method with the code editor.
		/// </summary>
		void InitializeComponent()
		{
			this->menu = (gcnew System::Windows::Forms::MenuStrip());
			this->fileMenu = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->editMenu = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->createMenu = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->viewMenu = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->optionMenu = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->helpMenu = (gcnew System::Windows::Forms::ToolStripMenuItem());
			this->menu->SuspendLayout();
			this->SuspendLayout();
			// 
			// menu
			// 
			this->menu->Items->AddRange(gcnew cli::array< System::Windows::Forms::ToolStripItem^  >(6) {
				this->fileMenu, this->editMenu,
					this->createMenu, this->viewMenu, this->optionMenu, this->helpMenu
			});
			this->menu->Location = System::Drawing::Point(0, 0);
			this->menu->Name = L"menu";
			this->menu->Size = System::Drawing::Size(852, 24);
			this->menu->TabIndex = 0;
			// 
			// fileMenu
			// 
			this->fileMenu->Name = L"fileMenu";
			this->fileMenu->Size = System::Drawing::Size(37, 20);
			this->fileMenu->Text = L"File";
			// 
			// editMenu
			// 
			this->editMenu->Name = L"editMenu";
			this->editMenu->Size = System::Drawing::Size(39, 20);
			this->editMenu->Text = L"Edit";
			// 
			// createMenu
			// 
			this->createMenu->Name = L"createMenu";
			this->createMenu->Size = System::Drawing::Size(53, 20);
			this->createMenu->Text = L"Create";
			// 
			// viewMenu
			// 
			this->viewMenu->Name = L"viewMenu";
			this->viewMenu->Size = System::Drawing::Size(44, 20);
			this->viewMenu->Text = L"View";
			// 
			// optionMenu
			// 
			this->optionMenu->Name = L"optionMenu";
			this->optionMenu->Size = System::Drawing::Size(61, 20);
			this->optionMenu->Text = L"Options";
			// 
			// helpMenu
			// 
			this->helpMenu->Name = L"helpMenu";
			this->helpMenu->Size = System::Drawing::Size(44, 20);
			this->helpMenu->Text = L"Help";
			// 
			// DallePad
			// 
			this->AutoScaleDimensions = System::Drawing::SizeF(6, 13);
			this->AutoScaleMode = System::Windows::Forms::AutoScaleMode::Font;
			this->ClientSize = System::Drawing::Size(852, 528);
			this->Controls->Add(this->menu);
			this->MainMenuStrip = this->menu;
			this->Name = L"DallePad";
			this->Text = L"DallePad - O Gadget que te transforma em um DJ";
			this->menu->ResumeLayout(false);
			this->menu->PerformLayout();
			this->ResumeLayout(false);
			this->PerformLayout();
		}
#pragma endregion
	};
}