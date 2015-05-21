/*
 * silvertunnel-ng.org Netlib - Java library to easily access anonymity networks
 * Copyright (c) 2013 silvertunnel-ng.org
 *
 * This program is free software; you can redistribute it and/or modify it
 * under the terms of the GNU General Public License as published by the Free
 * Software Foundation; either version 2 of the License, or (at your option)
 * any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or
 * FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for
 * more details.
 *
 * You should have received a copy of the GNU General Public License along
 * with this program; if not, see <http://www.gnu.org/licenses/>.
 */

package org.silvertunnel_ng.netlib.layer.tor.directory;

import android.test.InstrumentationTestCase;

import org.silvertunnel_ng.android_test.R;
import org.silvertunnel_ng.netlib.layer.tor.api.Fingerprint;
import org.silvertunnel_ng.netlib.layer.tor.api.Router;
import org.silvertunnel_ng.netlib.layer.tor.util.TorException;
import org.silvertunnel_ng.netlib.tool.ConvenientStreamReader;
import org.silvertunnel_ng.netlib.tool.ConvenientStreamWriter;
import org.silvertunnel_ng.netlib.util.DatatypeConverter;
import org.silvertunnel_ng.netlib.util.FileUtil;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.Map;


/**
 * Testing the {@link RouterImpl} class.
 *
 * @author Tobias Boese
 */
public final class RouterImplLocalTest extends InstrumentationTestCase {
    /**
     * Example descriptor from router : chaoscomputerclub27.
     */
    private static final String EXAMPLE_SERVER_DESCRIPTOR_PATH = "/org/silvertunnel_ng/netlib/layer/tor/example_router_descriptor.txt";
    /**
     * Example descriptors from all routers.
     */
    private static final String EXAMPLE_SERVER_DESCRIPTORS_PATH = "/org/silvertunnel_ng/netlib/layer/tor/example_router_descriptors.txt";
    private static final String ROUTER_BIN_PATH = "/org/silvertunnel_ng/netlib/layer/tor/directory/router.bin";

    private String descriptor;
    private String descriptors;

    /**
     * Test method for
     * {@link RouterImpl#RouterImpl(String)}
     * .
     *
     * @throws IOException
     * @throws TorException
     */
    public void testRouterImplTorConfigString() throws TorException, IOException {
        descriptor = FileUtil.readFileFromInputStream(getInstrumentation().getTargetContext().getResources().openRawResource(R.raw.example_router_descriptor));
        final Router testObject = new RouterImpl(descriptor);
        assertNotNull("parsing the descriptor didnt worked (should not return null)", testObject);
        assertEquals("wrong contact info", "J. Random Hacker <anonymizer@ccc.de>", testObject.getContact());
        assertEquals("wrong countrycode", "AT", testObject.getCountryCode());
        assertEquals("wrong nickname", "chaoscomputerclub27", testObject.getNickname());
        assertEquals("wrong tor version", "Tor 0.2.4.17-rc on Linux", testObject.getPlatform());
        assertTrue("wrong hidden service dir setting", testObject.isDirv2HSDir());
        assertEquals("wrong ip address", "77.244.254.227", testObject.getHostname());
        assertEquals("wrong or port", 443, testObject.getOrPort());
        assertEquals("wrong socks port", 0, testObject.getSocksPort());
        assertEquals("wrong dir port", 80, testObject.getDirPort());
        assertEquals("wrong fingerprint",
                new FingerprintImpl(DatatypeConverter.parseHexBinary("EEC954FB78B4FE48C6783FC3CB2E8562092890B8")),
                testObject.getFingerprint());
        assertEquals("wrong uptime", 5637742, testObject.getUptime());
        assertEquals("wrong number of family members", 11, testObject.getFamily().size());
        assertTrue("familymember 1 (11a0239fc6668705f68842811318b669c636f86e) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("11a0239fc6668705f68842811318b669c636f86e"))));
        assertTrue("familymember 2 (659df6537d605feab3b77e58e75342d704f0a799) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("659df6537d605feab3b77e58e75342d704f0a799"))));
        assertTrue("familymember 3 (71e78e9b961d5e25f1a16fccd15e81aa3b36cb93) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("71e78e9b961d5e25f1a16fccd15e81aa3b36cb93"))));
        assertTrue("familymember 4 (7610bbd3f5bb67284eee8476721ae6109dc29bea) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("7610bbd3f5bb67284eee8476721ae6109dc29bea"))));
        assertTrue("familymember 5 (7BE683E65D48141321C5ED92F075C55364AC7123) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("7BE683E65D48141321C5ED92F075C55364AC7123"))));
        assertTrue("familymember 6 (92d151a8219cc742de7e0eaeb6d18faf9793ba79) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("92d151a8219cc742de7e0eaeb6d18faf9793ba79"))));
        assertTrue("familymember 7 (9e9fad3187c9911b71849e0e63f35c7cd41faaa3) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("9e9fad3187c9911b71849e0e63f35c7cd41faaa3"))));
        assertTrue("familymember 8 (a9c039a5fd02fca06303dcfaabe25c5912c63b26) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("a9c039a5fd02fca06303dcfaabe25c5912c63b26"))));
        assertTrue("familymember 9 (d5edc74f2fb81e6ac1a8eba56448f71ddfaa4ae5) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("d5edc74f2fb81e6ac1a8eba56448f71ddfaa4ae5"))));
        assertTrue("familymember 10 (fbadb0598b2fe16aa1a078187620ec4c2df08451) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("fbadb0598b2fe16aa1a078187620ec4c2df08451"))));
        assertTrue("familymember 11 (fdba46e69d2dfa3fe165eeb84325e90b0b29bf07) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("fdba46e69d2dfa3fe165eeb84325e90b0b29bf07"))));
        assertEquals(1073741824, testObject.getBandwidthAvg());
        assertEquals(1258291200, testObject.getBandwidthBurst());
        assertEquals(4887529, testObject.getBandwidthObserved());
        assertFalse(testObject.isDirv2Authority());
        assertFalse(testObject.isDirv2Exit());
        assertFalse(testObject.isDirv2Fast());
        assertFalse(testObject.isDirv2Guard());
        assertFalse(testObject.isDirv2Named());
        assertFalse(testObject.isDirv2Running());
        assertFalse(testObject.isDirv2Stable());
        assertFalse(testObject.isDirv2V2dir());
        assertFalse(testObject.isDirv2Valid());
    }

    /**
     * Test method if it is possible to write a parsed Router to a file using own method.
     *
     * @throws TorException
     * @throws IOException
     */
    public void testWriteRouterToFile() throws TorException, IOException {
        descriptor = FileUtil.readFileFromInputStream(getInstrumentation().getTargetContext().getResources().openRawResource(R.raw.example_router_descriptor));
        final Router testObject = new RouterImpl(descriptor);
        File outputDir = getInstrumentation().getTargetContext().getCacheDir();
        File outputFile = File.createTempFile("router", "test", outputDir);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        ConvenientStreamWriter convenientStreamWriter = new ConvenientStreamWriter(fileOutputStream);
        testObject.save(convenientStreamWriter);
        fileOutputStream.close();

        FileInputStream fileInputStream = new FileInputStream(outputFile);
        ConvenientStreamReader convenientStreamReader = new ConvenientStreamReader(fileInputStream);
        Router testObject2 = new RouterImpl(convenientStreamReader);
        assertEquals(testObject, testObject2);
    }

    /**
     * Test method if it is possible to read a cached Router from a file using own method.
     *
     * @throws TorException
     * @throws IOException
     */
    public void testReadRouterFromFile() throws TorException, IOException {
        ConvenientStreamReader convenientStreamReader = new ConvenientStreamReader(getInstrumentation().getTargetContext().getResources().openRawResource(R.raw.router));
        Router testObject = new RouterImpl(convenientStreamReader);
        assertNotNull("parsing the descriptor didnt worked (should not return null)", testObject);
        assertEquals("wrong contact info", "J. Random Hacker <anonymizer@ccc.de>", testObject.getContact());
        assertEquals("wrong countrycode", "AT", testObject.getCountryCode());
        assertEquals("wrong nickname", "chaoscomputerclub27", testObject.getNickname());
        assertEquals("wrong tor version", "Tor 0.2.4.17-rc on Linux", testObject.getPlatform());
        assertEquals("wrong ip address", "77.244.254.227", testObject.getHostname());
        assertEquals("wrong or port", 443, testObject.getOrPort());
        assertEquals("wrong socks port", 0, testObject.getSocksPort());
        assertEquals("wrong dir port", 80, testObject.getDirPort());
        assertEquals("wrong fingerprint",
                new FingerprintImpl(DatatypeConverter.parseHexBinary("EEC954FB78B4FE48C6783FC3CB2E8562092890B8")),
                testObject.getFingerprint());
        assertEquals("wrong uptime", 5637742, testObject.getUptime());
        assertEquals("wrong number of family members", 11, testObject.getFamily().size());
        assertTrue("familymember 1 (11a0239fc6668705f68842811318b669c636f86e) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("11a0239fc6668705f68842811318b669c636f86e"))));
        assertTrue("familymember 2 (659df6537d605feab3b77e58e75342d704f0a799) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("659df6537d605feab3b77e58e75342d704f0a799"))));
        assertTrue("familymember 3 (71e78e9b961d5e25f1a16fccd15e81aa3b36cb93) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("71e78e9b961d5e25f1a16fccd15e81aa3b36cb93"))));
        assertTrue("familymember 4 (7610bbd3f5bb67284eee8476721ae6109dc29bea) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("7610bbd3f5bb67284eee8476721ae6109dc29bea"))));
        assertTrue("familymember 5 (7BE683E65D48141321C5ED92F075C55364AC7123) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("7BE683E65D48141321C5ED92F075C55364AC7123"))));
        assertTrue("familymember 6 (92d151a8219cc742de7e0eaeb6d18faf9793ba79) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("92d151a8219cc742de7e0eaeb6d18faf9793ba79"))));
        assertTrue("familymember 7 (9e9fad3187c9911b71849e0e63f35c7cd41faaa3) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("9e9fad3187c9911b71849e0e63f35c7cd41faaa3"))));
        assertTrue("familymember 8 (a9c039a5fd02fca06303dcfaabe25c5912c63b26) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("a9c039a5fd02fca06303dcfaabe25c5912c63b26"))));
        assertTrue("familymember 9 (d5edc74f2fb81e6ac1a8eba56448f71ddfaa4ae5) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("d5edc74f2fb81e6ac1a8eba56448f71ddfaa4ae5"))));
        assertTrue("familymember 10 (fbadb0598b2fe16aa1a078187620ec4c2df08451) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("fbadb0598b2fe16aa1a078187620ec4c2df08451"))));
        assertTrue("familymember 11 (fdba46e69d2dfa3fe165eeb84325e90b0b29bf07) not found",
                testObject.getFamily().contains(
                        new FingerprintImpl(DatatypeConverter.parseHexBinary("fdba46e69d2dfa3fe165eeb84325e90b0b29bf07"))));
        assertEquals(1073741824, testObject.getBandwidthAvg());
        assertEquals(1258291200, testObject.getBandwidthBurst());
        assertEquals(4887529, testObject.getBandwidthObserved());
        assertFalse(testObject.isDirv2Authority());
        assertFalse(testObject.isDirv2Exit());
        assertFalse(testObject.isDirv2Fast());
        assertFalse(testObject.isDirv2Guard());
        assertFalse(testObject.isDirv2Named());
        assertFalse(testObject.isDirv2Running());
        assertFalse(testObject.isDirv2Stable());
        assertFalse(testObject.isDirv2V2dir());
        assertFalse(testObject.isDirv2Valid());
    }

    /**
     * Test method if it is possible to write a parsed Router to a file using {@link ObjectOutputStream}.
     *
     * @throws TorException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void testWriteRoutersToFile() throws TorException, IOException, ClassNotFoundException {
        descriptors = FileUtil.readFileFromInputStream(getInstrumentation().getTargetContext().getResources().openRawResource(R.raw.example_router_descriptors));
        Directory directory = new Directory(null, null, null);
        final Map<Fingerprint, Router> allrouters = directory.parseRouterDescriptors(descriptors);
        assertNotNull(allrouters);
        assertFalse(allrouters.isEmpty());
        assertEquals(4648, allrouters.size());

        File outputDir = getInstrumentation().getTargetContext().getCacheDir();
        File outputFile = File.createTempFile("routers", "test", outputDir);
        FileOutputStream fileOutputStream = new FileOutputStream(outputFile);
        ConvenientStreamWriter convenientStreamWriter = new ConvenientStreamWriter(fileOutputStream);
        convenientStreamWriter.writeInt(allrouters.size());
        for (Router router : allrouters.values()) {
            router.save(convenientStreamWriter);
        }
        fileOutputStream.close();

        final Map<Fingerprint, Router> allrouters2 = new HashMap<Fingerprint, Router>();
        FileInputStream fileInputStream = new FileInputStream(outputFile);
        ConvenientStreamReader convenientStreamReader = new ConvenientStreamReader(fileInputStream);
        int count = convenientStreamReader.readInt();
        for (int i = 0; i < count; i++) {
            Router router = new RouterImpl(convenientStreamReader);
            allrouters2.put(router.getFingerprint(), router);
        }
        assertEquals(allrouters, allrouters2);
    }

    /**
     * Test method for
     * {@link Directory#parseRouterDescriptors(String)}
     * .
     *
     * @throws IOException when loading the example_server_descriptors from classpath didnt worked
     */
    public void testParseRouterDescriptors() throws IOException {
        Directory directory = new Directory(null, null, null);
        descriptors = FileUtil.readFileFromInputStream(getInstrumentation().getTargetContext().getResources().openRawResource(R.raw.example_router_descriptors));
        final Map<Fingerprint, Router> allrouters = directory.parseRouterDescriptors(descriptors);
        assertNotNull(allrouters);
        assertFalse(allrouters.isEmpty());
        assertEquals(4648, allrouters.size());
    }

}
