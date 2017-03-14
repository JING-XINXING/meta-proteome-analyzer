package de.mpa.client.settings;

import java.io.File;
import java.io.IOException;

import javax.swing.JComponent;

import de.mpa.client.ui.panels.SpectralLibrarySettingsPanel;

/**
 * Parameter map storing spectral similarity search settings.
 * @author A. Behne
 */
@SuppressWarnings("serial")
public class SpectralLibraryParameters extends ParameterMap {

	@Override
	public void initDefaults() {
        put("settings", new Parameter(new SpectralLibrarySettingsPanel(), null, null, "Settings") {
			@Override
			public JComponent createLeftComponent() {
				return (JComponent) getValue();
			}
			
			@Override
			public boolean applyChanges() {
				return false;
			}
			
		});
	}

	@Override
	public File toFile(String path) throws IOException {
		// do nothing, not needed
		return null;
	}

}
