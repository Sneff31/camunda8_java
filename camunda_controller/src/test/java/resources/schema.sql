DROP TABLE IF EXISTS "processDeployment";

CREATE TABLE "processDeployment" (
    "name" text,
    "businessKey" text,
    "deployment" text,
    "id" text NOT NULL,
    CONSTRAINT "processDeployment_pkey" PRIMARY KEY ("id")
);