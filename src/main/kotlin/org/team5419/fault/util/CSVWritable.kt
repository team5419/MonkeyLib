package org.team5419.fault.util

/**
 * Makes an object "CSVWritable" or able to be converted to CSV format.
 */
interface CSVWritable {
    /**
     * Writes the data in CSV formatting.
     */
    public fun toCSV(): String
}
