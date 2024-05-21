/*    
    Copyright (C) Usevalad Khatkevich
*/

package com.lushprojects.circuitjs1.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Button;
import com.lushprojects.circuitjs1.client.util.Locale;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.google.gwt.user.client.ui.CheckBox;
import com.google.gwt.storage.client.Storage;

public class ModDialog extends DialogBox {
	
	VerticalPanel vp;
	Storage lstor = Storage.getLocalStorageIfSupported();
	
	//for "UI scale:"
	HorizontalPanel scaleButtons;
	Button setScale100Button;
	Button setDefaultScaleButton;
	Button setScaleButton;
	HTML scaleScrollbarElm;
	native float getRealScale() /*-{
		return $wnd.nw.Window.get().zoomLevel;
	}-*/;
	String getScaleScrollbar(float value,int scale){
		return "<input type=\"range\" id=\"scaleUI\" oninput=\"getScaleInfo()\"" +
			"min=\"-0.5\" max=\"2\" step=\"0.1\" value=\""+value+"\"" +
			"style=\"width:355px\"><b><span class=\"scaleInfo\"" +
			"style=\"vertical-align:super\">"+scale+"%</span></b>";
	}

	//for "Top menu bar:"
	HorizontalPanel topMenuBarVars;
	CheckBox setStandartTopMenu;
	CheckBox setSmallTopMenu;

	//for "Start/Stop and Reset buttons:"
	HorizontalPanel btnsPreview;
	HTML previewText;
	Button resetPrevBtn;
	Button stopPrevBtn;
	HorizontalPanel SRCheckBoxes;
	VerticalPanel vp1;
	CheckBox setDefaultSRBtns;
	CheckBox setClassicSRBtns;
	VerticalPanel vp2;
	CheckBox setStopIcon;
	CheckBox setPauseIcon;
	VerticalPanel vp3;
	CheckBox hideSRBtns;

	//for "Other:"
	CheckBox setShowSidebaronStartup;


	Button closeButton;

	ModDialog() {

		super();
		vp = new VerticalPanel();
		setWidget(vp);
		setText("Modification Setup");
		vp.setWidth("400px");

		vp.add(new HTML("<big><b>UI Scale:</b></big>"));
		vp.add(scaleScrollbarElm = new HTML(getScaleScrollbar(getRealScale(),(int)(getRealScale()*100+100))));
		vp.setCellHorizontalAlignment(scaleScrollbarElm, HasHorizontalAlignment.ALIGN_CENTER);
		vp.add(scaleButtons = new HorizontalPanel());
		scaleButtons.setWidth("100%");
		scaleButtons.add(setScale100Button = new Button("100%",
			new ClickHandler(){
				public void onClick(ClickEvent event) {
					vp.remove(scaleScrollbarElm);
					vp.insert(scaleScrollbarElm = new HTML(getScaleScrollbar(0,100)),1);
					vp.setCellHorizontalAlignment(scaleScrollbarElm, HasHorizontalAlignment.ALIGN_CENTER);
					CirSim.executeJS("setScaleUI()");
					lstor.setItem("MOD_UIScale", "0");
				}
			}));
		scaleButtons.add(setDefaultScaleButton = new Button("Default scale<b>*</b>",
			new ClickHandler() {
				public void onClick(ClickEvent event) {
					vp.remove(scaleScrollbarElm);
					vp.insert(scaleScrollbarElm = new HTML(getScaleScrollbar(CirSim.getDefaultScale(),
						(int)(CirSim.getDefaultScale()*100+100))),1);
					vp.setCellHorizontalAlignment(scaleScrollbarElm, HasHorizontalAlignment.ALIGN_CENTER);
					CirSim.executeJS("setScaleUI()");
					lstor.setItem("MOD_UIScale", Float.toString(CirSim.getDefaultScale()));
				}
			}));
		scaleButtons.add(setScaleButton = new Button("Set",
			new ClickHandler() {
				public void onClick(ClickEvent event) {
					CirSim.executeJS("setScaleUI()");
					lstor.setItem("MOD_UIScale", Float.toString(getRealScale()));
				}
			}));
		vp.add(new HTML("<p>* - the default UI scale for your monitor is set to "+
			(int)(CirSim.getDefaultScale()*100+100)+"%</p>"));
		// Styling buttons:
		setScaleButton.addStyleName("modButtons"); //.setHeight("20px");
		setScaleButton.addStyleName("modSetButtons");
		setScale100Button.addStyleName("modButtons");
		setDefaultScaleButton.addStyleName("modButtons");
		//remove "gwt-Button" style:
		setScaleButton.removeStyleName("gwt-Button");
		setScale100Button.removeStyleName("gwt-Button");
		setDefaultScaleButton.removeStyleName("gwt-Button");
		scaleButtons.setCellHorizontalAlignment(setScale100Button, HasHorizontalAlignment.ALIGN_CENTER);
		scaleButtons.setCellHorizontalAlignment(setDefaultScaleButton, HasHorizontalAlignment.ALIGN_CENTER);
		scaleButtons.setCellHorizontalAlignment(setScaleButton, HasHorizontalAlignment.ALIGN_CENTER);

		vp.add(new HTML("<hr><big><b>Top Menu Bar:</b></big>"));
		vp.add(topMenuBarVars = new HorizontalPanel());
		topMenuBarVars.setWidth("100%");
		topMenuBarVars.add(setStandartTopMenu = new CheckBox("Standart"));
		topMenuBarVars.add(setSmallTopMenu = new CheckBox("Small"));
		if (CirSim.MENUBARHEIGHT<30) setSmallTopMenu.setValue(true);
		else setStandartTopMenu.setValue(true);

		setStandartTopMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (setSmallTopMenu.getValue()) {
					CirSim.MENUBARHEIGHT = 30;
					//CirSim.layoutPanel.setWidgetSize(menuBar, 30);
					setSmallTopMenu.setValue(false);
					setStandartTopMenu.setValue(true);
					CirSim.executeJS("CircuitJS1.redrawCanvasSize()");
					lstor.setItem("MOD_TopMenuBar", "standart");
				} else {
					setStandartTopMenu.setValue(true);
				}
			}
		});

		setSmallTopMenu.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				if (setStandartTopMenu.getValue()) {
					CirSim.MENUBARHEIGHT = 19;
					//CirSim.layoutPanel.setWidgetSize(menuBar, 19);
					setStandartTopMenu.setValue(false);
					setSmallTopMenu.setValue(true);
					CirSim.executeJS("CircuitJS1.redrawCanvasSize()");
					lstor.setItem("MOD_TopMenuBar", "small");
				} else {
					setSmallTopMenu.setValue(true);
				}
			}
		});

		// Styling checkboxes:
		topMenuBarVars.setCellHorizontalAlignment(setStandartTopMenu, HasHorizontalAlignment.ALIGN_CENTER);
		topMenuBarVars.setCellHorizontalAlignment(setSmallTopMenu, HasHorizontalAlignment.ALIGN_CENTER);
		
		vp.add(new HTML("<hr><big><b>Start/Stop and Reset Buttons:</b></big>"));
		vp.add(btnsPreview = new HorizontalPanel());
		btnsPreview.setWidth("100%");
		btnsPreview.setVerticalAlignment(HasVerticalAlignment.ALIGN_MIDDLE);
		btnsPreview.add(previewText = new HTML("<big>Preview:</big>"));
		btnsPreview.add(resetPrevBtn = new Button("&#8634;"));
		btnsPreview.add(stopPrevBtn = new Button("&#xE800;"));
		btnsPreview.setCellHorizontalAlignment(previewText, HasHorizontalAlignment.ALIGN_RIGHT);
		btnsPreview.setCellHorizontalAlignment(resetPrevBtn, HasHorizontalAlignment.ALIGN_RIGHT);
		btnsPreview.setCellHorizontalAlignment(stopPrevBtn, HasHorizontalAlignment.ALIGN_LEFT);
		// Временно(!):
		stopPrevBtn.setStyleName("run-stop-btn modDefaultRunStopBtn modPrevBtn");
		resetPrevBtn.setStyleName("reset-btn modDefaultResetBtn modPrevBtn");

		vp.add(SRCheckBoxes = new HorizontalPanel());
		SRCheckBoxes.setWidth("100%");
		SRCheckBoxes.add(vp1 = new VerticalPanel());
		SRCheckBoxes.add(vp2 = new VerticalPanel());
		SRCheckBoxes.add(vp3 = new VerticalPanel());
		vp3.setHeight("100%");
		SRCheckBoxes.setCellVerticalAlignment(vp3, HasVerticalAlignment.ALIGN_MIDDLE);
		//SRCheckBoxes.setCellHorizontalAlignment(vp3, HasHorizontalAlignment.ALIGN_CENTER);

		vp1.add(new HTML("<b>Theme:</b>"));
		vp1.add(setDefaultSRBtns = new CheckBox("Default"));
		vp1.add(setClassicSRBtns = new CheckBox("Classic"));

		vp2.add(new HTML("<b>Icon:</b>"));
		vp2.add(setStopIcon = new CheckBox("Stop"));
		vp2.add(setPauseIcon = new CheckBox("Pause"));

		vp3.add(hideSRBtns = new CheckBox("HIDE BUTTONS!"));

		if (CirSim.absResetBtn.getElement().hasClassName("modDefaultResetBtn"))
			setDefaultSRBtns.setValue(true);
		else setClassicSRBtns.setValue(true);

		/*if (CirSim.absRunStopBtn.getElement().getInnerText() == "&#xE800;")
			setStopIcon.setValue(true);
		else setPauseIcon.setValue(true);*/ //try to get info from localstorage

		setDefaultSRBtns.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (setClassicSRBtns.getValue()) {
						setClassicSRBtns.setValue(false);
						setDefaultSRBtns.setValue(true);
					} else {
						setDefaultSRBtns.setValue(true);
					}
				}
			});
		setClassicSRBtns.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (setDefaultSRBtns.getValue()) {
						setDefaultSRBtns.setValue(false);
						setClassicSRBtns.setValue(true);
					} else {
						setClassicSRBtns.setValue(true);
					}
				}
			});
		setStopIcon.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (setPauseIcon.getValue()) {
						setPauseIcon.setValue(false);
						setStopIcon.setValue(true);
					} else {
						setStopIcon.setValue(true);
					}
				}
			});
		setPauseIcon.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					if (setStopIcon.getValue()) {
						setStopIcon.setValue(false);
						setPauseIcon.setValue(true);
					} else {
						setPauseIcon.setValue(true);
					}
				}
			});
		hideSRBtns.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
				}
			});

		vp.add(new HTML("<hr><big><b>Other:</b></big>"));
		vp.add(setShowSidebaronStartup = new CheckBox("Show sidebar on startup"));
		vp.setCellHorizontalAlignment(setShowSidebaronStartup, HasHorizontalAlignment.ALIGN_CENTER);
		setShowSidebaronStartup.addClickHandler(new ClickHandler() {
				public void onClick(ClickEvent event) {
					
				}
			});
		vp.add(new HTML("<br>"));

		vp.add(closeButton = new Button("<b>Close</b>",
			new ClickHandler() {
				public void onClick(ClickEvent event) {
					closeDialog();
				}
			}));
		vp.setCellHorizontalAlignment(closeButton,
			HasHorizontalAlignment.ALIGN_CENTER);

		center();
		show();
	}

	protected void closeDialog() {
		hide();
	}

}