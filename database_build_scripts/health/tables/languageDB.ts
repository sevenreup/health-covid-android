import { Database, Statement } from "sqlite3";
import { Language } from "../data/language";

export async function populate(
  db: Database,
  LanguageList: Array<Language>
): Promise<void> {
  console.log("Populating Languages database...");
  await populateLanguage(db, LanguageList);
}

async function populateLanguage(db: Database, LanguageList: Array<Language>) {
  console.log("Populating Cities table...");
  db.serialize();
  await new Promise<void>((resolve, reject) => {
    db.run("begin transaction", function (err: Error) {
      if (null != err) {
        reject(err);
      } else {
        resolve();
      }
    });
  });
  const stmt = await new Promise<Statement>((resolve, reject) => {
    db.prepare("REPLACE INTO languages VALUES (?,?,?)", function (
      this: Statement,
      err: Error
    ) {
      if (null != err) {
        reject(err);
      } else {
        resolve(this);
      }
    });
  });

  for (const language of LanguageList) {
    await new Promise<void>((resolve, reject) => {
      stmt.run([
        language.languageId,
        language.language,
        language.country
      ], function (err: Error) {
        if (err != null) reject(err);
        else resolve();
      });
    });
  }
  console.log("languages populated.");
  await new Promise<void>((resolve, reject) => {
    stmt.finalize(function (err: Error) {
      if (null != err) {
        reject(err);
      } else {
        resolve();
      }
    });
  });
  await new Promise<void>((resolve, reject) => {
    db.run("commit", function (err: Error) {
      if (null != err) {
        reject(err);
      } else {
        resolve();
      }
    });
  });
}
