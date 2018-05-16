/*    
    Copyright (C) Usevalad Khatkevich
*/

package com.lushprojects.circuitjs1.client;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.DialogBox;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.ScrollPanel;

public class HelpDialog extends DialogBox {
	
    	HorizontalPanel hp;
	VerticalPanel vp;
	Button okButton;
	ScrollPanel sp;
	CirSim sim;
	
	HelpDialog() {
		super();
		vp = new VerticalPanel();
		setWidget(vp);
		setText(sim.LS("Help"));
		HTML contents = new HTML("<h3>Warning! If you work with the program using the keyboard, then after any interaction (except scrolling) with the sidebar, right-click on the empty area of the top panel. The disappearance of the functionality of the keys after interacting with the sidebar is a feature of the JavaScript interpreted by the NW.js shell.</h3>" +
		"<h3>The program works only with the English keyboard layout.</h3><hr>" +
		"<h2>Contents:</h2>\n" + 
		"    <ol type=l>\n" + 
		"        <li>\n" + 
		"            <p>\n" + 
		"                <a href=\"#p1\">How to use this</a>\n" + 
		"            </p>" +
		"<li>\n" + 
		"                <p>\n" + 
		"                    <a href=\"#p2\">Drawing and Editing Circuits</a>\n" + 
		"                </p>\n" + 
		"                <li>\n" + 
		"                    <p>\n" + 
		"                        <a href=\"#p3\">High Frequency Circuits</a>\n" + 
		"                    </p>\n" + 
		"                    <li>\n" + 
		"                        <p>\n" + 
		"                            <a href=\"#p4\">Simulation != Real Life</a>\n" + 
		"                        </p>\n" + 
		"                        <li>\n" + 
		"                            <p>\n" + 
		"                                <a href=\"#p5\">Some Errors</a>\n" + 
		"                            </p>\n" + 
		"                            <li>\n" + 
		"                                <p>\n" + 
		"                                    <a href=\"#p6\">License</a>\n" + 
		"                                </p>\n" + 
		"    </ol>\n" + 
		"    <h4>The main part of the information is taken from the http://lushprojects.com/circuitjs/\n" + 
		"    </h4>\n" + 
		"    <form action=\"#\" method=\"GET\">\n" + 
		"        <fieldset>\n" + 
		"            <legend>\n" + 
		"                <h2 id=\"p1\">How to use this</h2>\n" + 
		"            </legend>\n" + 
		"            <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">When the simulator starts up you will see an animated schematic of a simple LRC circuit. The green colour indicates\n" + 
		"                positive voltage. The grey colour indicates ground. A red colour indicates negative voltage. The moving yellow\n" + 
		"                dots indicate current.</p>\n" + 
		"            <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">To turn a switch on or off, just click on it. If you move the mouse over any component of the circuit, you will\n" + 
		"                see a short description of that component and its current state in the lower right corner of the window.\n" + 
		"                To modify a component, move the mouse over it, click the right mouse button (or control-click if you have\n" + 
		"                a Mac) and select &quot;Edit&quot;. You can also access the edit function by double-clicking on a component.</p>\n" + 
		"            <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">There are three graphs at the bottom of the window; these act like oscilloscopes, each one showing the voltage\n" + 
		"                and current across a particular component. Voltage is shown in green, and current is shown in yellow. The\n" + 
		"                current may not be visible if the voltage graph is on top of it. The peak value of the voltage in the scope\n" + 
		"                window is also shown. Move the mouse over one of the scope views, and the component it is graphing will be\n" + 
		"                highlighted. To modify or remove a scope, click the right mouse button over it and choose &quot;remove&quot;\n" + 
		"                from the menu. There are also many other scope options in this context menu. To view a component in the scope,\n" + 
		"                click the right mouse button over the component and select &quot;View in Scope&quot;.</p>\n" + 
		"            <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">The &quot;Circuits&quot; menu contains a lot of sample circuits for you to try.</p>\n" + 
		"            <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">Some circuits, eg Basics-&gt;Potentiometer, contain potentiometers or variable voltage sources. These can be\n" + 
		"                adjusted using sliders that are added to the right hand tool bar, or by positioning the mouse pointer over\n" + 
		"                the component and using the scroll wheel.</p>\n" + 
		"        </fieldset>\n" + 
		"<br>\n" + 
		"        <form action=\"#\" method=\"GET\">\n" + 
		"            <fieldset>\n" + 
		"                <legend>\n" + 
		"                    <h2 id=\"p2\">Drawing and Editing Circuits</h2>\n" + 
		"                </legend>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">You can get a blank circuit by choosing &quot;Blank Circuit&quot; from the &quot;Circuits&quot; menu. You\n" + 
		"                    will need to add at least one voltage source to start the simulator.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">To add components or a wire choose one of the &quot;Add....&quot; options from the &quot;Draw&quot; menu.\n" + 
		"                    Note that common components have keyboard short-cuts to select their add mode. When in add mode the cursor\n" + 
		"                    changes to a &quot;+&quot;. Click and drag the mouse to add a component.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">Components may be moved and resized in the selection mode. When in selection mode the cursor changes to an\n" + 
		"                    arrow. Choose &quot;Select/Drag Sel&quot; from the &quot;Draw&quot; menu or press &quot;space&quot;,\n" + 
		"                    or press &quot;escape&quot; to go in to selection mode. Hovering over a component will highlight it and\n" + 
		"                    show information about that component in the info area. Clicking and dragging on a component will move\n" + 
		"                    the component. If you click and drag on the square handles or hold down the ctrl key this will resize\n" + 
		"                    the component and move the terminals.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">Wires only connect at their ends and not in the middle therefore you have to draw each segment of a wire\n" + 
		"                    separately. If the simulator spots unconnected points it thinks you intended to connect it will highlight\n" + 
		"                    these with a red circle.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">Many components have adjustments that can be made using the edit function explained above. For resistors,\n" + 
		"                    capacitors and inductors you can conveniently set the value from the E12 range by rolling the mouse wheel\n" + 
		"                    when hovering over the component</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">The File menu allows you to import or export circuit description files. See notes below on browser compatibility.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">The Reset button resets the circuit to a reasonable state. If the simulation is paused then hitting the Reset\n" + 
		"                    button twice will restart it. The Run/Stop button allows you to pause the simulation. The Simulation\n" + 
		"                    Speed slider allows you to adjust the speed of the simulation. If the simulation isn&#39;t time-dependent\n" + 
		"                    (that is, if there are no capacitors, inductors, or time-dependent voltage sources), then this won&#39;t\n" + 
		"                    have any effect. The Current Speed slider lets you adjust the speed of the dots, in case the currents\n" + 
		"                    are so weak (or strong) that the dots are moving too slowly (or too quickly).</p>\n" + 
		"            </fieldset>\n" + 
		"        </form>\n" + 
		"<br>\n" + 
		"        <form action=\"#\" method=\"GET\">\n" + 
		"            <fieldset>\n" + 
		"                <legend>\n" + 
		"                    <h2 id=p3>High Frequency Circuits</h2>\n" + 
		"                </legend>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">This simulator simulates the circuit using a series of short time steps. In each step the changes to the\n" + 
		"                    voltages and currents in the circuit are calculated based on the component models and the current circuit\n" + 
		"                    state. For this process to work the time steps used need to be significantly shorter than the duration\n" + 
		"                    of any event of interest in the circuit. Or, if you prefer, the time steps need to be significantly shorter\n" + 
		"                    than the period of the highest frequency signal of interest.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">By default the simulator uses a 5&micro;s step size. This is OK for audio frequency signals but not for radio\n" + 
		"                    frequency signals or fast digital signals. The step size can be changed from the &quot;Other Options...&quot;\n" + 
		"                    dialog on the options menu. For comparison, the transmission line example in the application uses a 5ps\n" + 
		"                    step size.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">The step size shouldn&#39;t be confused with the &quot;Simulation Speed&quot; controlled by the slider in\n" + 
		"                    the right hand panel. The step size controls how long (in simulated time) each step is. The &quot;Simulation\n" + 
		"                    Speed&quot; slider controls how often (in real time) the computer calculates a step.</p>\n" + 
		"\n" + 
		"            </fieldset>\n" + 
		"        </form>\n" + 
		"<br>\n" + 
		"        <form action=\"#\" method=\"GET\">\n" + 
		"            <fieldset>\n" + 
		"                <legend>\n" + 
		"                    <h2 id=p4>Simulation != Real Life</h2>\n" + 
		"                </legend>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">Physics simulations are not real life, and don&#39;t assume that simulation and reality are identical! This\n" + 
		"                    simulation idealizes many components. Wires and component leads have no resistance. Voltage sources are\n" + 
		"                    ideal - they will try and supply infinite current if you let them. Capacitors and inductors are 100%\n" + 
		"                    efficient. Logic gate inputs draw zero current - not too bad as an approximation for CMOS logic, but\n" + 
		"                    not typical of 1980s TTL for example. By all means use this simulator to help visualize circuits, but\n" + 
		"                    always test in reality.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">Sorry to break it to you folks, but the simulator numerically approximates models of components that are\n" + 
		"                    also approximate. Even without allowing for any bugs it is just a rough guide to reality. This simulator\n" + 
		"                    may be helpful for visualization, but used the wrong way any simulator can give a false sense of security.\n" + 
		"                    Some people don&#39;t really grasp this important concept - I&#39;ve even had one user accuse the simulator\n" + 
		"                    of &quot;lying&quot; because he (or she) didn&#39;t take account of the component idealizations and didn&#39;t\n" + 
		"                    understand the actual performance of the components they chose to use. It&#39;s a key leaning for all\n" + 
		"                    electronic engineers that they must always be fully aware of real-world component (and system) characteristics\n" + 
		"                    and how these differ from any particular simulator they use. If you want more precise models of real-world\n" + 
		"                    components then the SPICE-based simulators are much more appropriate tools than this one, but even then,\n" + 
		"                    you should be aware of deviations from reality. As the great analogue circuit designer Bob Pease said\n" + 
		"                    &quot;When a computer tries to simulate an analog circuit, sometimes it does a good job; but when it\n" + 
		"                    doesn&#39;t, things get very sticky&quot;.</p>\n" + 
		"                <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">One consequence of the use of ideal components is that the simulator doesn&#39;t converge on a result for\n" + 
		"                    circuits that have no defined behaviour - for example an ideal voltage source short-circuited by an ideal\n" + 
		"                    wire. Another situation that can&#39;t be simulated under these assumptions is the current distribution\n" + 
		"                    between the conductors if two perfect conductors are connected in parallel. When using the simulator\n" + 
		"                    you must account for places where real electronics differs from the ideal.</p>\n" + 
		"            </fieldset>\n" + 
		"        </form>\n" + 
		"<br>\n" + 
		"        <form action=\"#\" method=\"GET\">\n" + 
		"            <fieldset>\n" + 
		"                <legend>\n" + 
		"                    <h2 id=\"p5\">Some Errors</h2>\n" + 
		"                </legend>\n" + 
		"                <p style=\"font-family: Arial, Helvetica, sans-serif;\">Here are some errors you might encounter:</p>\n" + 
		"                <ul style=\"margin-top: 0px; margin-bottom: 0px; font-family: Arial, Helvetica, sans-serif;\">\n" + 
		"                    <li style=\"text-align: justify;\">Voltage source loop with no resistance! - this means one of the voltage sources in your circuit is shorted.\n" + 
		"                        Make sure there is some resistance across every voltage source.</li>\n" + 
		"                    <li style=\"text-align: justify;\">Capacitor loop with no resistance! - it&#39;s not allowed to have any current loops containing capacitors\n" + 
		"                        but no resistance. For example, capacitors connected in parallel are not allowed; you must put a\n" + 
		"                        resistor in series with them. Shorted capacitors are allowed.</li>\n" + 
		"                    <li style=\"text-align: justify;\">Singular matrix! - this means that your circuit is inconsistent (two different voltage sources connected\n" + 
		"                        to each other), or that the voltage at some point is undefined. It might mean that some component&#39;s\n" + 
		"                        terminals are unconnected; for example, if you create an op-amp but haven&#39;t connected anything\n" + 
		"                        to it yet, you will get this error.</li>\n" + 
		"                    <li style=\"text-align: justify;\">Convergence failed! - this means the simulator can&#39;t figure out what the state of the circuit should\n" + 
		"                        be. Just click Reset and hopefully that should fix it. Your circuit might be too complicated, but\n" + 
		"                        this happens sometimes even with the examples.</li>\n" + 
		"                    <li style=\"text-align: justify;\">Transmission line delay too large! - the transmission line delay is too large compared to the timestep\n" + 
		"                        of the simulator, so too much memory would be required. Make the delay smaller.</li>\n" + 
		"                    <li style=\"text-align: justify;\">Need to ground transmission line! - the bottom two wires of a transmission line must always be grounded\n" + 
		"                        in this simulator.</li>\n" + 
		"                </ul>\n" + 
		"            </fieldset>\n" + 
		"<br>\n" + 
		"            <form action=\"#\" method=\"GET\">\n" + 
		"                <fieldset>\n" + 
		"                    <legend>\n" + 
		"                        <h2 id=\"p6\">License</h2>\n" + 
		"                    </legend>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">The simulator is provided with no support or warranty. Absolutely no guarantee is provided of suitability\n" + 
		"                        for any purpose.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">This program is free software; you can redistribute it and/or modify it under the terms of the GNU General\n" + 
		"                        Public License as published by the Free Software Foundation; either version 2 of the License, or\n" + 
		"                        (at your option) any later version.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even\n" + 
		"                        the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General\n" + 
		"                        Public License for more details.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">For details of licensing see http://www.gnu.org/licenses/.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\"> Original by Paul Falstad.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">http://www.falstad.com/</p> \n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">JavaScript conversion by Iain Sharp.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">http://lushprojects.com/</p>   \n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">The program was ported to Windows, MAC OS and Linux by Usevalad Khatkevich.</p>\n" + 
		"                    <p align=\"justify\" style=\"font-family: Arial, Helvetica, sans-serif;\">https://github.com/CEBA77</p>\n" + 
		"                </fieldset>" +
		"<br />");
		vp.add(sp= new ScrollPanel(contents));
		sp.setWidth("500px");
		sp.setHeight("400px");
		HorizontalPanel hp = new HorizontalPanel();
		hp.setWidth("100%");
		hp.setHorizontalAlignment(HasHorizontalAlignment.ALIGN_LEFT);
		hp.setStyleName("topSpace");
		vp.add(hp);
		hp.add(okButton = new Button("OK"));
		okButton.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				closeDialog();
			}
		});
		center();
		show();
	}

	protected void closeDialog() {
		hide();
	}
}