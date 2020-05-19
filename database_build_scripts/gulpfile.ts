import * as path from 'path';
import * as fs from 'fs';

const SCHEMA_PATH = path.join(__dirname, "..", "app", "schemas", "com.skybox.seven.covid.data.AppDatabase");
const DEST_DB = path.join(__dirname, "..", "app", "src", "main", "assets", "databases", "health.db")

const VERSION_PATH = path.join(__dirname, "..", "app", "gradle.properties");