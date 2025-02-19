DROP TABLE IF EXISTS "processdeployment";

CREATE TABLE "processdeployment" (
    "name" text,
    "businesskey" text,
    "deployment" text,
    "id" text NOT NULL,
    CONSTRAINT "processdeployment_pkey" PRIMARY KEY ("id")
);