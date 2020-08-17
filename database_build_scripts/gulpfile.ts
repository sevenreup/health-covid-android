import * as path from 'path';
import * as fs from 'fs';
import minimist, {ParsedArgs} from "minimist";
import { health } from './health';

const SCHEMA_PATH = path.join(__dirname, "..", "app", "schemas", "com.skybox.seven.covid.data.AppDatabase");
const DEST_DB = path.join(__dirname, "..", "app", "src", "main", "assets", "databases", "health.db")

const VERSION_PATH = path.join(__dirname, "..", "app", "gradle.properties");

const VERSION_KEY = "HEALTH_DB_VERSION"

interface Args extends ParsedArgs {
    dataDir: string
}

/**
 * Increments database version which room uses to guess if new data is available
 * @param file Path to properties
 * @param key Key to set version to
 */
function incrementDatabaseVersion(file: string, key: string) {
    const versionRegex = new RegExp(`${key}\\s*=\\s*(\\d+)`, "gi");
    let contents = fs.readFileSync(file, "utf8");
    const matches = versionRegex.exec(contents);
    if (null === matches) {
        throw new Error("Did not find match in specified file");
    }

    const version = parseInt(matches[1]);
    const newVersion = version + 1;

    console.log("Current version: " + version + ". New version: " + newVersion);

    contents = contents.replace(versionRegex, `${key}=${newVersion}`);

    fs.writeFileSync(file, contents)
}

export async function healthRun(): Promise<any> {
    const options: Args = minimist<Args> (
        process.argv.slice(2), {
            string: ["dataDir"],
            default: { dataDir: "_data"}
        }
    );
    const pathToNewDb = await health(SCHEMA_PATH, options.dataDir);
    // 2. Copy to assets
    fs.copyFileSync(pathToNewDb, DEST_DB);
    // 3. Increment version
    incrementDatabaseVersion(VERSION_PATH, VERSION_KEY);
}