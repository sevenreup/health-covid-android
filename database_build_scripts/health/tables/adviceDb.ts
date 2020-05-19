import { Database, Statement } from "sqlite3";
import { Advice } from "../data/advice";

export async function populate(
  db: Database,
  adviceList: Array<Advice>
): Promise<void> {
  console.log("Populating Advice database...");
  await populateAdvice(db, adviceList);
}

async function populateAdvice(db: Database, adviceList: Array<Advice>) {
  console.log("Populating Advice table...");
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
    db.prepare("REPLACE INTO advices VALUES (?,?,?,?,?,?)", function (
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

  for (const advice of adviceList) {
    await new Promise<void>((resolve, reject) => {
      stmt.run([], function (err: Error) {
        if (err != null) reject(err);
        else resolve();
      });
    });
  }
  console.log("Advice populated.");
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
