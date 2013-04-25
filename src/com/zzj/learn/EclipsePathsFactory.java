package com.zzj.learn;

import org.eclipse.core.resources.ResourcesPlugin;
import org.eclipse.core.runtime.Platform;

public class EclipsePathsFactory {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		Platform.getLocation();
//		  System.out.println(ResourcesPlugin.getWorkspace());
		  System.out.println(ResourcesPlugin.getWorkspace().getRoot().getLocation().toOSString());
//		  Platform.getInstanceLocation();
//		System.out.println(Platform.getInstallLocation());
	}

}
